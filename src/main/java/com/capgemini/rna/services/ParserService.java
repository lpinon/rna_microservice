package com.capgemini.rna.services;

import com.capgemini.rna.models.Codon;
import com.capgemini.rna.models.Gen;
import com.capgemini.rna.models.exceptions.AParserHandledException;
import com.capgemini.rna.models.exceptions.EmptyGenException;
import com.capgemini.rna.models.exceptions.InvalidCharacterException;
import com.capgemini.rna.models.exceptions.UnexpectedEndOfStringException;
import com.capgemini.rna.models.responses.DecoderResponse;
import lombok.extern.java.Log;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
@Scope("singleton")
@Log
public class ParserService {

    @Value("#{'${codons.stop}'.split(',')}")
    private List<String> stopCodonsProp;

    @Autowired
    private StoreService store;

    @Autowired
    private SynchroService synchro;

    private String missingChars = "";
    private HashSet<Integer> stopCodons;

    @PostConstruct
    public void init() throws InvalidCharacterException {
        this.stopCodons = new HashSet<Integer>();
        for (String enc : this.stopCodonsProp) {
            this.stopCodons.add(new Codon(enc.charAt(0), enc.charAt(1), enc.charAt(2)).getCode());
        }
    }

    private String removeComments(String str) {
        int commentPosition = str.indexOf('>');
        String withNoComments;
        if (commentPosition >= 0) {
            if(commentPosition == 0) {
                withNoComments = "";
            } else {
                withNoComments = str.substring(0, commentPosition);
            }
        } else {
            withNoComments = str;
        }
        return withNoComments;
    }

    protected String normalize(String str) {
        String normalized = str.toUpperCase().replace(" ", "");
        normalized = this.removeComments(normalized);
        return normalized;
    }

    protected void handleNewCodon(Codon codon, String id) {
        if(this.isEndCodon(codon)){
            if (!this.isCurrentGenReady(id)) {
                this.store.addCodon(codon, id);
                this.handleGenReady(id);
            } else {
                log.info("Gen already complete skipping: " + codon.getIdentifier() );
            }
        } else {
            if(this.isCurrentGenReady(id)) {
                this.store.initNewGen(id);
            }
            this.store.addCodon(codon, id);
        }
    }

    private void handleGenReady(String id) {
        this.store.saveAsComputed(id);
        // TODO Send to stream or save to DB
    }

    public void handleNewRNAString(String string, String id) throws AParserHandledException {
        String cleanLine = this.normalize(string);
        if(cleanLine.length() > 0) {
            if(this.store.getMissingChars(id).length() > 0){
                cleanLine = this.store.getMissingChars(id) + cleanLine;
                this.store.cleanMissingChars(id);
            }
            try {
                for(var i = 0; i < cleanLine.length(); i += 3) {
                    try {
                        Codon codon = new Codon(cleanLine.charAt(i), cleanLine.charAt(i + 1), cleanLine.charAt(i + 2));
                        this.handleNewCodon(codon, id);
                    } catch (IndexOutOfBoundsException ex) {
                        throw new UnexpectedEndOfStringException(i);
                    }
                }
            } catch (UnexpectedEndOfStringException e) {
                this.store.setMissingChars(cleanLine.substring(e.getPosition()), id);
            }
        }
    }

    public boolean isCurrentGenReady(String id) {
        boolean isReady;
        try {
            isReady = this.isEndCodon(this.store.getCurrentGen(id).getLastCodon());
        } catch (EmptyGenException e) {
            isReady = false;
        }
        return isReady;
    }

    private boolean isEndCodon(Codon codon) {
        return this.stopCodons.contains(codon.getCode());
    }

    public DecoderResponse parseRNAMultilineString(String multilineString, String id) throws InterruptedException {
        this.synchro.acquireExecutionPermission(id);
        String[] lines = multilineString.split("\n");
        for (var i = 0; i < lines.length; i++) {
            try {
                this.handleNewRNAString(lines[i], id);
            } catch (AParserHandledException e) {
                log.severe("Parser exception on line " + i + " for id " + id);
                log.severe(e.getMessage());
                e.setLine(i);
                this.store.saveException(e, id);
            }
        }
        ArrayList<AParserHandledException> exceptions = this.store.getExceptions(id);
        ArrayList<Gen> gens = this.store.getComputed(id);
        if(this.store.getCurrentGen(id).getCodons().size() > 0 && !this.store.getCurrentGen(id).isGenValid()) {
            gens.add(this.store.getCurrentGen(id));
        }
        log.info("Parsed " + gens.size() + " gens for id " + id);
        log.warning("Thrown " + exceptions.size() + " exceptions for id " + id);
        // Generate response obj
        DecoderResponse response = new DecoderResponse(gens, exceptions);
        // Clean memory
        this.store.initNewExceptionsGroup(id);
        this.store.initNewComputedGroup(id);
        this.synchro.release(id);
        return response;
    }
}

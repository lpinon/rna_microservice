package com.capgemini.rna.services;

import com.capgemini.rna.models.Codon;
import com.capgemini.rna.models.exceptions.AParserHandledException;
import com.capgemini.rna.models.exceptions.EmptyGenException;
import com.capgemini.rna.models.exceptions.InvalidCharacterException;
import com.capgemini.rna.models.exceptions.UnexpectedEndOfStringException;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
@Scope("singleton")
@Log
public class ParserService {

    @Value("#{'${codons.stop}'.split(',')}")
    private List<String> stopCodonsProp;

    @Autowired
    private StoreService store;

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
                log.info("Gen already complete skipping: " + codon.getIdentificator() );
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
            if(this.missingChars.length() > 0){
                cleanLine = this.missingChars + cleanLine;
                this.missingChars = "";
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
                this.missingChars = cleanLine.substring(e.getPosition());
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

    public void parseRNAMultilineString(String multilineString, String id) {
        String[] lines = multilineString.split("\n");
        for (String line : lines) {
            try {
                this.handleNewRNAString(line, id);
            } catch (AParserHandledException e) {
                log.severe(e.getMessage());
                this.store.saveException(e, id);
            }
        }
        log.info("Parsed " + this.store.getComputed(id).size() + " gens");
        log.warning("Thrown " + this.store.getExceptions(id).size() + " exceptions");
        // Clean memory
        this.store.initNewExceptionsGroup(id);
        this.store.initNewComputedGroup(id);
    }
}

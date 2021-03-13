package com.capgemini.rna.services;

import com.capgemini.rna.models.Codon;
import com.capgemini.rna.models.Gen;
import com.capgemini.rna.models.exceptions.AParserHandledException;
import com.capgemini.rna.models.exceptions.InvalidCharacterException;
import lombok.extern.java.Log;
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

    private ArrayList<Gen> computedGens = new ArrayList<Gen>();
    private Gen currentGen = new Gen();
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

    private String normalize(String str) {
        var normalized = str.toUpperCase().replace(" ", "");
        normalized = removeComments(str);
        return normalized;
    }

    public void handleNewRNAString(String line) throws AParserHandledException {
        String cleanLine = this.normalize(line);

        if(this.currentGen.isGenValid()) {
            this.currentGen = new Gen();
        }
        // this.currentGen.addCodon(codon);
    }

    private boolean isEndCodon(Codon codon) {
        return this.stopCodons.contains(codon.getCode());
    }

    public void parseRNAMultilineString(String multilineString) {
        this.computedGens = new ArrayList<>();
        ArrayList<AParserHandledException> exceptions = new ArrayList<>();
        String[] lines = multilineString.split("\n");
        for (String line : lines) {
            try {
                this.handleNewRNAString(line);
            } catch (AParserHandledException e) {
                log.severe(e.getMessage());
                exceptions.add(e);
            }
        }
    }
}

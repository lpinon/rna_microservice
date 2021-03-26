package com.capgemini.rna.models;

import com.capgemini.rna.models.exceptions.EmptyGenException;
import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

import java.util.ArrayList;

public class Gen {

    @Getter
    private ArrayList<Codon> codons = new ArrayList<>();

    public void addCodon(Codon codon) {
        this.codons.add(codon);
    }

    public Codon getLastCodon() throws EmptyGenException {
        if (this.codons.size() == 0) {
            throw new EmptyGenException();
        }
        return this.codons.get(this.codons.size() - 1);
    }

    public boolean isGenValid() {
        return this.codons.size() > 1;
    }

    public String asString() {
        String out = "";
        for(Codon codon: this.codons) {
            out += codon.getIdentifier();
        }
        return out;
    }
}

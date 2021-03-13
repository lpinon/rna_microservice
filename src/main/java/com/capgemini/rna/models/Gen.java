package com.capgemini.rna.models;

import com.capgemini.rna.models.exceptions.EmptyGenException;

import java.util.ArrayList;

public class Gen {

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
}

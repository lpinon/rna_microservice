package com.capgemini.rna.models;

import java.util.ArrayList;

public class Gen {

    private ArrayList<Codon> codons = new ArrayList<>();

    public void addCodon(Codon codon) {
        this.codons.add(codon);
    }

    public Codon getLastCodon() {
        return this.codons.get(this.codons.size() - 1);
    }

    public boolean isGenValid() {
        return this.codons.size() > 1;
    }
}

package com.capgemini.rna.models;

import com.capgemini.rna.models.exceptions.EmptyGenException;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

public class Gen {

    @JsonProperty
    public String getIdentifier() {
        StringBuilder out = new StringBuilder();
        for(Codon codon: this.codons) {
            out.append(codon.getIdentifier());
        }
        return out.toString();
    }

    @Getter
    private ArrayList<Codon> codons = new ArrayList<>();

    public void addCodon(Codon codon) {
        this.codons.add(codon);
    }

    @JsonIgnore
    public Codon getLastCodon() throws EmptyGenException {
        if (this.codons.size() == 0) {
            throw new EmptyGenException();
        }
        return this.codons.get(this.codons.size() - 1);
    }

    @JsonIgnore
    public boolean isGenValid() {
        return this.codons.size() > 1;
    }


}

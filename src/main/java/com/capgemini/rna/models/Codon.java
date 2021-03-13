package com.capgemini.rna.models;

import com.capgemini.rna.models.exceptions.InvalidCharacterException;
import lombok.Getter;

public class Codon {

    @Getter private final int code;

    public Codon(Character nucleotide1, Character nucleotide2, Character nucleotide3) throws InvalidCharacterException {
        this.code = this.parseNucleotides(nucleotide1, nucleotide2, nucleotide3);
    }

    private int parseNucleotides(Character nucleotide1, Character nucleotide2, Character nucleotide3) throws InvalidCharacterException {
        int nuc1code = this.getNucleotideCode(nucleotide1);
        int nuc2code = this.getNucleotideCode(nucleotide2);
        int nuc3code = this.getNucleotideCode(nucleotide3);
        return nuc1code * 100 + nuc2code * 10 + nuc3code;
    }

    private int getNucleotideCode(Character nucleotide) throws InvalidCharacterException {
        switch (nucleotide) {
            case 'A':
                return 2;
            case 'U':
                return 3;
            case 'G':
                return 4;
            case 'C':
                return 5;
            default:
                throw new InvalidCharacterException(nucleotide);
        }
    }

}

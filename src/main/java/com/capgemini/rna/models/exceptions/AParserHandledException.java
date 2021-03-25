package com.capgemini.rna.models.exceptions;

import lombok.Getter;
import lombok.Setter;

public abstract class AParserHandledException extends Exception {
    @Setter @Getter
    private int line;

    public AParserHandledException(String s) {
        super(s);
    }
    public AParserHandledException() {
    }
}

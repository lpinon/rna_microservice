package com.capgemini.rna.models.exceptions;

import lombok.Getter;

public class UnexpectedEndOfStringException extends AParserHandledException {
    @Getter private final int position;

    public UnexpectedEndOfStringException(int pos) {
        super("Unexpected end of input string");
        this.position = pos;
    }
}

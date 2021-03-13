package com.capgemini.rna.models.exceptions;

public class EmptyGenException extends AParserHandledException {
    public EmptyGenException() {
        super("Empty gen");
    }
}

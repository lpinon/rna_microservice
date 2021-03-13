package com.capgemini.rna.models.exceptions;

public class UnexpectedEndOfStringException extends AParserHandledException {
    public UnexpectedEndOfStringException() { super("Unexpected end of input string"); }
}

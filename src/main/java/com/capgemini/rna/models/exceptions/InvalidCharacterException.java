package com.capgemini.rna.models.exceptions;

public class InvalidCharacterException extends AParserHandledException {
    public InvalidCharacterException(Character character) {
        super("Invalid character " + character);
    }
}

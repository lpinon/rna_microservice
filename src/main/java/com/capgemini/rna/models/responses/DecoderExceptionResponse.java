package com.capgemini.rna.models.responses;

import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Getter
public class DecoderExceptionResponse {

    @NotNull
    private int line;

    @NotNull
    private String error;

    public DecoderExceptionResponse(int line, String error) {
        this.line = line;
        this.error = error;
    }

}

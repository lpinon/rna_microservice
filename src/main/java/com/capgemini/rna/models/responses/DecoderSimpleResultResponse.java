package com.capgemini.rna.models.responses;

import lombok.Getter;

public class DecoderSimpleResultResponse {

    @Getter private String id;
    @Getter private String gen;
    @Getter private String error;

    public DecoderSimpleResultResponse(String gen, String id, String error) {
        this.id = id;
        this.gen = gen;
        this.error = error;
    }

}

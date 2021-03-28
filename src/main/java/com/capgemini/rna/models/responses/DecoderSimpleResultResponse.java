package com.capgemini.rna.models.responses;

import lombok.Getter;

public class DecoderSimpleResultResponse {

    @Getter private String id;
    @Getter private String gen;

    public DecoderSimpleResultResponse(String gen, String id) {
        this.id = id;
        this.gen = gen;
    }

}

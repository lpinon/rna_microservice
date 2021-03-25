package com.capgemini.rna.models.responses;

import com.capgemini.rna.models.Gen;
import lombok.Getter;

public class DecoderResultResponse {
    @Getter private Gen gen;

    public DecoderResultResponse(Gen gen) {
        this.gen = gen;
    }
}

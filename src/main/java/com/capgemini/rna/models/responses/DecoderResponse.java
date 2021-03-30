package com.capgemini.rna.models.responses;

import com.capgemini.rna.models.Gen;
import com.capgemini.rna.models.exceptions.AParserHandledException;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class DecoderResponse {
    private final ArrayList<DecoderResultResponse> results;
    private final ArrayList<DecoderExceptionResponse> exceptions;

    public DecoderResponse(ArrayList<Gen> gens, ArrayList<AParserHandledException> exceptions) {
        this.exceptions = new ArrayList<>();
        for(AParserHandledException exception: exceptions) {
            this.exceptions.add(new DecoderExceptionResponse(exception.getLine(), exception.getMessage()));
        }
        this.results = new ArrayList<>();
        for(Gen gen: gens) {
            this.results.add(new DecoderResultResponse(gen));
        }
    }
}

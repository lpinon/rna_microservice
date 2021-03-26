package com.capgemini.rna.models.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class DecoderRequest {
    @NotNull
    @JsonProperty("id")
    private String id;

    @NotNull
    @JsonProperty("data")
    private String data;
}
package com.capgemini.rna.controllers;

import com.capgemini.rna.models.requests.DecoderRequest;
import com.capgemini.rna.models.responses.DecoderResponse;
import com.capgemini.rna.services.ParserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class ParseController {

    @Autowired
    ParserService parser;

    @RequestMapping( value="/decode", method = RequestMethod.POST)
    public DecoderResponse decode(@NotNull @Valid @RequestBody DecoderRequest request) throws InterruptedException {
        return parser.parseRNAMultilineString(request.getData(), request.getId());
    }

}

package com.capgemini.rna.tests;

import com.capgemini.rna.app.MainApplication;
import com.capgemini.rna.models.Gen;
import com.capgemini.rna.models.responses.DecoderResponse;
import com.capgemini.rna.models.responses.DecoderSimpleResultResponse;
import com.capgemini.rna.services.KafkaService;
import com.capgemini.rna.services.ParserService;
import lombok.extern.java.Log;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Collectors;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;


@Log
@SpringBootTest(classes = MainApplication.class)
@ActiveProfiles("kafka-test")
@Disabled
public class ComponentTests {

    @Autowired
    ParserService parserService;

    @Autowired
    KafkaService kafkaService;

    private static String exampleStringChunk1 = ">NM_0002\naugugcgag";
    private static String exampleStringChunk2 = "gacugcuga \n>NM_0003 \n";
    private static String exampleStringChunk3 = "augugcgaguag";
    private static String exampleString = ">NM_0002\n" +
            "augugcgag gacugcuga \n" +
            ">NM_0003 \n" +
            "augugcgaguag";
    private static String endOfStream = "END_OF_STREAM";

    private int getValidCount(DecoderResponse res) {
        return (int) res.getResults().stream().filter(el -> el.getGen().isGenValid()).count();
    }

    @Test
    public void whenReceiveInputInChunks_returnExpectedGens() throws InterruptedException {
        int numGensTotal = parserService.parseRNAMultilineString(exampleString, "test-total").getResults().size();
        int countedGens = 0;
        int countedExceptions = 0;
        DecoderResponse res = parserService.parseRNAMultilineString(exampleStringChunk1, "test-chunked");
        countedGens += this.getValidCount(res);
        countedExceptions += res.getExceptions().size();
        res = parserService.parseRNAMultilineString(exampleStringChunk2, "test-chunked");
        countedGens += this.getValidCount(res);
        countedExceptions += res.getExceptions().size();
        res = parserService.parseRNAMultilineString(exampleStringChunk3, "test-chunked");
        countedGens += this.getValidCount(res);
        countedExceptions += res.getExceptions().size();
        assert countedGens == numGensTotal;
        assert countedExceptions == 1;
    }

    @Test
    public void whenReceiveInputChunksEndBeforeCompleteGen_returnException() throws InterruptedException {
        ArgumentCaptor<DecoderSimpleResultResponse> responseCaptor = ArgumentCaptor.forClass(DecoderSimpleResultResponse.class);
        parserService.handleNewRNAStream(exampleStringChunk1, "test-chunked");
        parserService.handleNewRNAStream(endOfStream, "test-chunked");
        verify(kafkaService, atLeastOnce()).sendResult(responseCaptor.capture());
        responseCaptor.getValue().getError().equals("Unexpected end of input string");
    }

    @Test
    public void whenReceiveInputInChunksInParallel_returnExpectedGens() throws InterruptedException {
        int numGensTotal = parserService.parseRNAMultilineString(exampleString, "test-parallel-total").getResults().size();
        int countedGens1 = 0;
        int countedExceptions1 = 0;
        int countedGens2 = 0;
        int countedExceptions2 = 0;
        int countedGens3 = 0;
        int countedExceptions3 = 0;

        DecoderResponse res = parserService.parseRNAMultilineString(exampleStringChunk1, "test-parallel-chunked-1");
        countedGens1 += this.getValidCount(res);
        countedExceptions1 += res.getExceptions().size();
        res = parserService.parseRNAMultilineString(exampleStringChunk1, "test-parallel-chunked-2");
        countedGens2 += this.getValidCount(res);
        countedExceptions2 += res.getExceptions().size();
        res = parserService.parseRNAMultilineString(exampleStringChunk1, "test-parallel-chunked-3");
        countedGens3 += this.getValidCount(res);
        countedExceptions3 += res.getExceptions().size();

        res = parserService.parseRNAMultilineString(exampleStringChunk2, "test-parallel-chunked-1");
        countedGens1 += this.getValidCount(res);
        countedExceptions1 += res.getExceptions().size();
        res = parserService.parseRNAMultilineString(exampleStringChunk2, "test-parallel-chunked-2");
        countedGens2 += this.getValidCount(res);
        countedExceptions2 += res.getExceptions().size();
        res = parserService.parseRNAMultilineString(exampleStringChunk2, "test-parallel-chunked-3");
        countedGens3 += this.getValidCount(res);
        countedExceptions3 += res.getExceptions().size();

        res = parserService.parseRNAMultilineString(exampleStringChunk3, "test-parallel-chunked-1");
        countedGens1 += this.getValidCount(res);
        countedExceptions1 += res.getExceptions().size();
        res = parserService.parseRNAMultilineString(exampleStringChunk3, "test-parallel-chunked-2");
        countedGens2 += this.getValidCount(res);
        countedExceptions2 += res.getExceptions().size();
        res = parserService.parseRNAMultilineString(exampleStringChunk3, "test-parallel-chunked-3");
        countedGens3 += this.getValidCount(res);
        countedExceptions3 += res.getExceptions().size();

        assert countedGens1 == numGensTotal;
        assert countedExceptions1 == 1;
        assert countedGens2 == numGensTotal;
        assert countedExceptions2 == 1;
        assert countedGens3 == numGensTotal;
        assert countedExceptions3 == 1;
    }

}
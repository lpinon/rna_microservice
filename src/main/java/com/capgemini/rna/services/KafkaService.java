package com.capgemini.rna.services;

import com.capgemini.rna.models.requests.DecoderRequest;
import com.capgemini.rna.models.responses.DecoderSimpleResultResponse;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Log
@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, DecoderSimpleResultResponse> kafkaTemplate;

    @Value(value = "${kafka.clean.topic}")
    private String cleanTopic;

    @Autowired
    private ParserService parser;

    @KafkaListener(
            topics = "rawsamples",
            containerFactory = "requestsListenerContainerFactory")
    public void requestsListener(DecoderRequest request) throws InterruptedException {
        this.parser.handleNewRNAStream(request.getData(), request.getId());
    }

    public void sendResult(DecoderSimpleResultResponse result) {
        ListenableFuture<SendResult<String, DecoderSimpleResultResponse>> future = kafkaTemplate.send(cleanTopic, result);
        if(future != null) {
            future.addCallback(new ListenableFutureCallback<SendResult<String, DecoderSimpleResultResponse>>() {

                @Override
                public void onSuccess(SendResult<String, DecoderSimpleResultResponse> msg) {
                    log.info("Sent message with offset=[" + msg.getRecordMetadata().offset() + "]");
                }

                @Override
                public void onFailure(Throwable ex) {
                    log.severe("Unable to send message on due to : " + ex.getMessage());
                }
            });
        }
    }

}

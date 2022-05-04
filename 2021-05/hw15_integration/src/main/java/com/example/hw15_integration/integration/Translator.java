package com.example.hw15_integration.integration;

import com.example.hw15_integration.domain.Sentence;
import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;

import java.util.Collection;
import java.util.List;

@MessagingGateway
public interface Translator {

    @Gateway(requestChannel = "sentenceChannel", replyChannel = "allWordChannel")
    List<String> process(Sentence sentence);
}

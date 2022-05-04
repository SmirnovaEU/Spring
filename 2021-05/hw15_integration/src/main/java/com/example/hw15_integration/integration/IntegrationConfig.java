package com.example.hw15_integration.integration;

import com.example.hw15_integration.service.TranslateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.scheduling.PollerMetadata;

@Configuration
public class IntegrationConfig {
    private static final int QUEUE_CAPACITY = 10;
    private static final String GET_WORDS_METHOD_NAME = "getSentenceWords";
    private static final String WORDS_LOW_CASE = "wordToLowerCase";
    private static final String WORDS_ROUTER = "wordRouter";
    private static final String WRITE_TRANSLATE = "writeTranslate";

    @Bean
    public QueueChannel sentenceChannel() {
        return MessageChannels.queue(QUEUE_CAPACITY).get();
    }

    @Bean
    public PublishSubscribeChannel allWordChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel filteredChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel translatedChannel() {
        return MessageChannels.publishSubscribe().get();
    }

    @Bean
    public PublishSubscribeChannel newWordChannel() {
        return MessageChannels.publishSubscribe().get();
    }


    @Bean(name = PollerMetadata.DEFAULT_POLLER)
    public PollerMetadata poller() {
        return Pollers.fixedRate(100).maxMessagesPerPoll(2).get();
    }

    @Bean
    public IntegrationFlow wordFlow(TranslateService translateService) {
        return IntegrationFlows.from(sentenceChannel())

                .handle(translateService, GET_WORDS_METHOD_NAME)
                .transform(translateService, WORDS_LOW_CASE)
                .channel(allWordChannel())
                .split()
                .filter(WordFilter::isLongerMin)
                .channel(filteredChannel())
                .route(translateService, WORDS_ROUTER)
                .get();
    }

    @Bean
    public IntegrationFlow translateFlow(TranslateService translateService) {
        return IntegrationFlows.from(translatedChannel())

                .handle(translateService, WRITE_TRANSLATE)
                .get();
    }

}

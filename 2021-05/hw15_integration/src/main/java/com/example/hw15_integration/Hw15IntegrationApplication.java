package com.example.hw15_integration;

import com.example.hw15_integration.service.WordService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class Hw15IntegrationApplication {

    public static void main(String[] args) throws Exception {
        AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(Hw15IntegrationApplication.class);

        WordService service = ctx.getBean(WordService.class);
        service.startWordsLoop();

    }

}

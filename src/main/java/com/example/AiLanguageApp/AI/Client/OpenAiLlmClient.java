package com.example.AiLanguageApp.AI.Client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.openai.client.OpenAIClient;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

@Component
public class OpenAiLlmClient implements LlmClient {

    private final OpenAIClient openAIClient;
    private final String model;

    public OpenAiLlmClient(
            OpenAIClient openAIClient,
            @Value("${openai.model}") String model) {

        this.openAIClient = openAIClient;
        this.model = model;
    }

    @Override
    public String generateResponse(String prompt) {

        ResponseCreateParams params =
                ResponseCreateParams.builder()
                        .model(model)
                        .input(prompt)
                        .build();

        Response response =
                openAIClient.responses()
                        .create(params);

        return response.output()
                .stream()
                .filter(item -> item.isMessage())
                .map(item -> item.asMessage())
                .flatMap(message -> message.content().stream())
                .filter(content -> content.isOutputText())
                .map(content -> content.asOutputText().text())
                .findFirst()
                .orElse("");
    }
}
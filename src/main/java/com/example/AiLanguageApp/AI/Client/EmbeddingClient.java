package com.example.AiLanguageApp.AI.Client;

import java.util.List;

public interface EmbeddingClient {

    List<Double> generateEmbedding(String text);
}
package com.example.AiLanguageApp.AI.Rag;

import java.util.List;

public interface VectorStore {

    void store(
            String id,
            List<Double> embedding,
            String content
    );

    List<String> search(
            List<Double> embedding,
            int limit
    );
}
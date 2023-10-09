package com.keyin;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class SuggestionEngineTest {
    SuggestionEngine suggestionEngine = new SuggestionEngine();
    @org.junit.jupiter.api.Test
    void loadDictionaryData() {
        try {
            try {
                suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.jupiter.api.Test
    void setWordSuggestionDB() {
        try {
            suggestionEngine.setWordSuggestionDB(suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

    }

    @org.junit.jupiter.api.Test
    void generateSuggestions() {
        try {
            suggestionEngine.setWordSuggestionDB(suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI())));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }

        String suggestions = suggestionEngine.generateSuggestions("ello");
        System.out.println(suggestions);

    }

    @org.junit.jupiter.api.Test
    void getWordSuggestionDB() {
    }
}
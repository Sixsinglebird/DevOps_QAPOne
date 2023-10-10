package com.keyin;

import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

class SuggestionEngineTest {
    SuggestionEngine suggestionEngine = new SuggestionEngine();
    @org.junit.jupiter.api.Test
    void loadDictionaryData() throws URISyntaxException, IOException {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
    }

    @org.junit.jupiter.api.Test
    void generateSuggestions() throws IOException, URISyntaxException {
        suggestionEngine.loadDictionaryData(Paths.get( ClassLoader.getSystemResource("words.txt").toURI()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("hellp").contains("notaword"));

    }
}
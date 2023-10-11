package com.keyin;

import org.junit.jupiter.api.Assertions;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;

public class SuggestionEngineTest {
    SuggestionEngine suggestionEngine = new SuggestionEngine();
    @org.junit.jupiter.api.Test
    public void loadDictionaryData() throws URISyntaxException, IOException {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
    }

    @org.junit.jupiter.api.Test
    public void generateSuggestions() throws IOException, URISyntaxException {
        suggestionEngine.loadDictionaryData(Paths.get( ClassLoader.getSystemResource("words.txt").toURI()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("hellp").contains("sdafasdfgasdfg"));

    }
}

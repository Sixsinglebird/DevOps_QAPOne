package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class SuggestionEngineTest {
    SuggestionEngine suggestionEngine = new SuggestionEngine();

    @Test
    public void testLoadDictionaryData() throws URISyntaxException, IOException {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").toURI()));
        Assertions.assertNotNull(suggestionEngine.getWordSuggestionDB());
    }

    @Test
    public void testSetWordSuggetionDB() throws URISyntaxException, IOException {
        suggestionEngine.setWordSuggestionDB(new SuggestionsDatabase());
        Assertions.assertNotNull(suggestionEngine);
    }

   @Test
    public void testGenerateSuggestions() throws IOException, URISyntaxException {
        suggestionEngine.loadDictionaryData(Paths.get( ClassLoader.getSystemResource("words.txt").toURI()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("hellp").contains("hello"));
    }
}

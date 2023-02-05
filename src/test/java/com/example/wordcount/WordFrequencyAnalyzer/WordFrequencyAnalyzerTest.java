package com.example.wordcount.WordFrequencyAnalyzer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.wordcount.WordFrequency.WordFrequency;



public class WordFrequencyAnalyzerTest {

    private WordFrequencyAnalyzerImpl wordFrequencyAnalyzer;

    //sample texts
    private final String BLANK_TEXT = "";
    private final String GIBBERISH = "@%%@^ ";
    private final String TWO_DISTINCT_WORDS_SINGLE_INSTANCE = "true false";
    private final String SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE = "aap aap zebra beEr Beer beER cheeta";

    // TO TEST:
    // List<WordFrequency> calculateMostFrequentNWords (String text, int n);

    @BeforeEach
    private void setup() {
        wordFrequencyAnalyzer = new WordFrequencyAnalyzerImpl();
    }
 
// int calculateHighestFrequency(String text);

    @Test
    void calculateHighestFrequencyReturnsCorrectValue() {
        //SORTABLE_TEXT_WITH_DUPLICATES contains three instances of one word
        assertEquals(wordFrequencyAnalyzer.calculateHighestFrequency(SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE),3);
        //TWO_DISTINCT_WORDS_SINGLE_INSTANCE contains 1 instance of each word
        assertEquals(wordFrequencyAnalyzer.calculateHighestFrequency(TWO_DISTINCT_WORDS_SINGLE_INSTANCE),1);
    }

    @Test
    void calculateHighestFrequencyReturnsZeroOnInvalidInput() {
        //BLANK_TEXT is flagged as invalid input and returns zero
        assertEquals(wordFrequencyAnalyzer.calculateHighestFrequency(BLANK_TEXT),0);

        //GIBBERISH is flagged as invalid input and returns zero
        assertEquals(wordFrequencyAnalyzer.calculateHighestFrequency(GIBBERISH),0);
    }


// int calculateFrequencyForWord (String text, String word);
    @Test
    void calculateFrequencyForWordReturnsCorrectValue() {
        //SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE contains 3 instances of the word "beer"
        assertEquals(wordFrequencyAnalyzer.calculateFrequencyForWord(SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE, "beer"),3);
        //SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE contains 1 instances of the word "cheeta"
        assertEquals(wordFrequencyAnalyzer.calculateFrequencyForWord(SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE, "cheeta"),1);
        //SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE contains 0 instances of the word "fiets"
        assertEquals(wordFrequencyAnalyzer.calculateFrequencyForWord(SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE, "fiets"),0);
        //SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE contains 0 instances GIBBERISH
        assertEquals(wordFrequencyAnalyzer.calculateFrequencyForWord(SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE, GIBBERISH),0);
    }

    @Test
    void calculateFrequencyForWordReturnsZeroOnInvalidInput() {
        //blank text is invalid, returns 0
        assertEquals(wordFrequencyAnalyzer.calculateFrequencyForWord(BLANK_TEXT, "random"),0);
        //blank search word is invalid, returns 0
        assertEquals(wordFrequencyAnalyzer.calculateFrequencyForWord(SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE, BLANK_TEXT),0);
        //GIBBERISH does not match any WordFrequency, returns 0
        assertEquals(wordFrequencyAnalyzer.calculateFrequencyForWord(SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE, GIBBERISH),0);
    }

// List<WordFrequency> calculateMostFrequentNWords (String text, int n);
    @Test
    void calculateMostFrequentNWordsReturnsCorrectValues() {
        //SORTABLE_TEXT_WITH_DUPLICATES contains three instances of one word - selecting that word should result in a value of 3
        List <WordFrequency> result = wordFrequencyAnalyzer.calculateMostFrequentNWords(SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE,2);
        assertEquals(result.get(0).getFrequency(),3);
        //TWO_DISTINCT_WORDS_SINGLE_INSTANCE contains 1 instance of each word - selecting that word should result in a value of 1
        result = wordFrequencyAnalyzer.calculateMostFrequentNWords(TWO_DISTINCT_WORDS_SINGLE_INSTANCE, 2);
        assertEquals(result.get(0).getFrequency(),1);
    }

    @Test
    void calculateMostFrequentNWordsReturnsCorrectOrder() {
        //"aap aap zebra beEr Beer beER cheeta" should yield, in order: beer (3x)/ aap (2x) / cheeta (1x  - first alphabetically) / zebra (1x  - later alphabetically)
        List <WordFrequency> result = wordFrequencyAnalyzer.calculateMostFrequentNWords(SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE,4);
        assertTrue(result.get(0).getWord().equalsIgnoreCase("beer"));
        assertTrue(result.get(1).getWord().equalsIgnoreCase("aap"));
        assertTrue(result.get(2).getWord().equalsIgnoreCase("cheeta"));
        assertTrue(result.get(3).getWord().equalsIgnoreCase("zebra"));
    }

    @Test
    void calculateMostFrequentNWordsReturnsBlanksIfNExceedsTotalNUmberOfWordFrequencies() {
        //if value n exceeeds the number of words provided in the text input, calculateMostFrequentNWords will return a Collection of size n, topped up with blanks
        //SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE should yield a List of 4 WordFrequency objects, asking for 5 will yield 1 BLANK
        List <WordFrequency> result = wordFrequencyAnalyzer.calculateMostFrequentNWords(SORTABLE_TEXT_WITH_DUPLICATES_MULTI_CASE,5);
        assertTrue(result.get(4).getWord().equalsIgnoreCase("blank"));
    }
    
}

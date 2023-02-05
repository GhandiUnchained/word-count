package com.example.wordcount.WordFrequencyAnalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.wordcount.WordFrequency.WordFrequency;
import com.example.wordcount.WordFrequency.WordFrequencyImpl;

import lombok.Getter;

@Getter
@Component
public class WordFrequencyAnalyzerImpl implements WordFrequencyAnalyzer {
    private List<WordFrequency> wordFrequencies;

    @Override
    public int calculateHighestFrequency(String text) {
        if (inputIsValid(text)) {
            wordFrequencies = new ArrayList<WordFrequency>();
            convertInputStringToWordFrequencies(text.trim());
            sortWordFrequencies();
            if (wordFrequencies.size()>0) {
                return wordFrequencies.get(0).getFrequency();
            }
        }
        return 0;
    }

    @Override
    public int calculateFrequencyForWord(String text, String word) {
        if (inputIsValid(text, word)) {
            wordFrequencies = new ArrayList<WordFrequency>();
            convertInputStringToWordFrequencies(text.trim());
            WordFrequency wordFrequency = findWordInCollection(word.trim());
            if (wordFrequency != null) {
                return wordFrequency.getFrequency();
            } 
        }
        return 0;
    }

    @Override
    public List<WordFrequency> calculateMostFrequentNWords(String text, int n) { // TODO: confer with PO, determine what to do if n > number of words in text
        if (inputIsValid(text, n)) {
            wordFrequencies = new ArrayList<WordFrequency>();
            convertInputStringToWordFrequencies(text.trim());
            sortWordFrequencies();
            List<WordFrequency> result = new ArrayList<>();
            for (int i = 0 ; i < n ; i++) {
                if (i >= wordFrequencies.size()) {
                    result.add(new WordFrequencyImpl("BLANK", 0));
                } else {
                    result.add(wordFrequencies.get(i));
                }
            }
            return result;
        }
        return null;
    }

    private boolean inputIsValid(String text) {
        return !(text.trim().equals(""));
    }

    private boolean inputIsValid(String text, String word) {
        return !(text.trim().equals("") || word.trim().equals(""));
    }

    private boolean inputIsValid(String text, int n) {
        return !(text.trim().equals("") || n==0);
    }



    private void sortWordFrequencies() {
        Comparator <WordFrequency> wordFrequenciesComparator 
            = Comparator.comparing(
                WordFrequency::getFrequency, (wf1, wf2) -> {
                    return wf2.compareTo(wf1);
                })
                .thenComparing(WordFrequency::getWord);
        Collections.sort(wordFrequencies, wordFrequenciesComparator);
    }

    // TODO: confer with PO: brief states a-zA-Z can only appear in words. does this mean exclusively these are allowed || loose character =!allowed
    private void convertInputStringToWordFrequencies(String text) { 
        for (String s : text.split("[^a-zA-Z]+")) {
            if (wordFrequencies.size() > 0) {
                WordFrequency presentInCollection = findWordInCollection(s);
   
                if (presentInCollection == null) {
                    wordFrequencies.add(new WordFrequencyImpl(s,1));
                } else {
                    presentInCollection.incrementFrequency();
                }
            } else {
                wordFrequencies.add(new WordFrequencyImpl(s,1));
            }
        }
    }
    
    private WordFrequency findWordInCollection(String s) {
        for (WordFrequency wf: wordFrequencies) {
            if (wf.getWord().equalsIgnoreCase(s)) {
                return wf;
            }
        }
        return null;
    }

    //DEBUG   DEBUG   DEBUG   DEBUG   DEBUG   DEBUG   DEBUG   DEBUG   DEBUG
    private void printWordFrequencies() {
        wordFrequencies.stream().forEach(wq -> System.out.println(wq.getWord() + " present " + wq.getFrequency() + " times"));
    }
}

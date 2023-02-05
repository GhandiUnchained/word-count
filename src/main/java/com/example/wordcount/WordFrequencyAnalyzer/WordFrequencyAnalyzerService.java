package com.example.wordcount.WordFrequencyAnalyzer;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.wordcount.WordFrequency.WordFrequency;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class WordFrequencyAnalyzerService {
    private WordFrequencyAnalyzer wordFrequencyAnalyzer;

    public int calculateHighestFrequency(String text) {
        return wordFrequencyAnalyzer.calculateHighestFrequency(text);
    }

    public int calculateFrequencyForWord (String text, String word) {
        return wordFrequencyAnalyzer.calculateFrequencyForWord(text, word);
    }

    public List<WordFrequency> calculateMostFrequentNWords (String text, int n) {
        return wordFrequencyAnalyzer.calculateMostFrequentNWords(text, n);
    }
    
}

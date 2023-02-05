package com.example.wordcount.WordFrequencyAnalyzer;

import java.util.List;

import com.example.wordcount.WordFrequency.WordFrequency;

public interface WordFrequencyAnalyzer {
    int calculateHighestFrequency(String text);
    int calculateFrequencyForWord (String text, String word);
    List<WordFrequency> calculateMostFrequentNWords (String text, int n);
}

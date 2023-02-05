package com.example.wordcount.WordFrequencyAnalyzer;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.wordcount.WordFrequency.WordFrequency;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(path="word-count")
public class WordFrequencyAnalyzerController {
    private WordFrequencyAnalyzerService wordFrequencyAnalyzerService;

    @GetMapping("/highest-frequency")
    public int calculateHighestFrequency(String text) {
        return wordFrequencyAnalyzerService.calculateHighestFrequency(text);
    }

    @GetMapping("/word-frequency")
    public int calculateFrequencyForWord (String text, String word) {
        return wordFrequencyAnalyzerService.calculateFrequencyForWord(text, word);
    }
    
    @GetMapping("most-frequent-words")
    List<WordFrequency> calculateMostFrequentNWords (String text, int n) {
        return wordFrequencyAnalyzerService.calculateMostFrequentNWords(text, n);
    }
}

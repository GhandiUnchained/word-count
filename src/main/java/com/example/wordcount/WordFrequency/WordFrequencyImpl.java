package com.example.wordcount.WordFrequency;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Setter
@AllArgsConstructor
public class WordFrequencyImpl implements WordFrequency {
    String word;
    int frequency;

    @Override
    public String getWord() {
        return word;
    }

    @Override
    public int getFrequency() {
        return frequency;
    }
    
    public void incrementFrequency() {
        frequency++;
    }
}

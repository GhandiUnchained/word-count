# word-count
Assessment - counting words

ENDPOINTS are available on:

*   word-count/highest-frequency
Takes a String 'text'
Returns an integer indicating how often the word with the most occurances is present in the provided text

*   word-count/word-frequency
Takes a String 'text' and a String 'word'
Returns an integer indicating how often the specified word occurs in the text.

*   word-count/most-frequent-words
Takes a String 'text' and an integer 'n'
Returns a List containing n number of <WordFrequency>
The List is sorted to have the words with most occurances at the top. Words with same number of occurances are sorted alphabetically.
If n exceeds the number of distinct words in the supplied text, the List will auto-fill with blank WordFrequency objects and return a List of size n.




#2023-feb-05 TODO

re: WordFrequencyAnalyzer calculateMostFrequentNWords
confer with PO, determine what to do if n > number of words in text

re: WordFrequencyAnalyzer sortWordFrequencies
confer with PO: brief states a-zA-Z can only appear in words. does this mean exclusively these are allowed || loose character =!allowed

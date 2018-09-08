package main.utils;

import java.util.HashSet;

public class Result {
    HashSet<String> dictionary;
    WordBreak wordBreak;

    public Result(HashSet<String> dictionary) {
        this.dictionary = dictionary;
        wordBreak = new WordBreak(dictionary);
    }

    /*
     * @param original: original number
     *        number:  valid number without any punctuation
     * main process function which calls PossibleWord to get all possibility
     * Each word is given to wordBreak class to valid words from Dictionary
     */
    public void process(String orginal, String number) {
        PossibleWords possiblity = new PossibleWords();
        HashSet<String> possibleWords = possiblity.getWords(number);
        HashSet<String> output = new HashSet<>();
        for (Object words : possibleWords) {
            output.addAll(wordBreak.wordBreak((String) words));
        }
        print(orginal, output);
    }

    /*
     * @param original: original phone number provided by user
     *        output: possible words for given number
     *  an utility function for printing the result
     */
    public void print(String original, HashSet<String> output) {
        if (output.size() == 0) {
            System.out.println(original + " " + Constants.skipped);
            return;
        }
        System.out.println("Possible matches for " + original);
        output.forEach(System.out::println);
    }
}
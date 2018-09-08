package main.utils;

import java.util.HashSet;


import static main.utils.Constants.hashTable;

public class PossibleWords {
    public static HashSet<String> possibleWords;

    public PossibleWords() {
        possibleWords = new HashSet<>();
    }

    /*
     *@param number: a valid phone number
     * returns all possible words by replacing every digit
     */
    public HashSet<String> getWords(String number) {
        String empty = "";
        if (number.equals(empty))
            return new HashSet<>();
        int start = 0;
        getWordsUtil(number, start, empty);
        return possibleWords;
    }

    /*
     * @param: number: a valid phone number
     *         index: recursive function parameter for current index of word
      *        word: recursive function parameter for intermediate word
     * an utility function to generate all possible words
     */
    public void getWordsUtil(String number, int index, String word) {
        if (index == number.length()) {
            possibleWords.add(word);
            return;
        }
        int digit = number.charAt(index) - '0';
        if (hashTable[digit].length() == 0) {
            getWordsUtil(number, index + 1, word + digit);
        } else {
            for (int i = 0; i < hashTable[digit].length(); i++) {
                getWordsUtil(number, index + 1, word + hashTable[digit].substring(i, i + 1));
            }
        }
    }
}
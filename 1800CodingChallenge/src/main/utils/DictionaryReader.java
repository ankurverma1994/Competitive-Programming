/*
 * This program is made in response to ACONEX's Coding Challenge #1.
 * By: Ankur Narain Verma
 */
package main.utils;

import main.exception.FileReading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashSet;

public class DictionaryReader {
    String location;
    HashSet<String> dictionary;

    /*
     * Default Dictionary Initialisation
     */
    public DictionaryReader() {
        dictionary = new HashSet<>();
        location = defaultDictionaryLocation();
    }

    /*
     * Initialises user defined dictionary
     */
    public DictionaryReader(String location) {
        dictionary = new HashSet<>();
        this.location = location;
    }

    String defaultDictionaryLocation() {
        return System.getProperty("user.dir") + "/dic.txt";
    }

    /*
     * Read word from Dictionary file and validates it
     * returns dictionary HashSet consisting words of dictionary
     */
    public HashSet<String> processDictionary() throws FileReading {
        BufferedReader buffer;
        String tempString;
        try {
            buffer = new BufferedReader(new FileReader(location));
            while ((tempString = buffer.readLine()) != null) {
                storeWord(tempString);
            }

        } catch (Exception e) {
            throw new FileReading(Constants.dictionaryReadError);
        }
        return dictionary;
    }

    /*
     * @param word: Word from dictionary file
     * Removes all whitespace and punctuation from the provided text.
     */
    public void storeWord(String word) {
        String result = "";
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (small(ch) || capital(ch))
                result += ch;
        }
        result = result.toUpperCase();
        if (!result.equals(""))
            dictionary.add(result);
    }

    /*
     * @param c: a character text
     * returns true if c is small letter english alphabet
     */
    boolean small(char c) {
        return 'a' <= c && c <= 'z';
    }

    /*
     * @param c:  a character text
     * returns true if c is capital letter english alphabet
     */
    boolean capital(char c) {
        return 'A' <= c && c <= 'Z';
    }
}
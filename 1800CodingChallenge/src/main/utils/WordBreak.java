package main.utils;

import java.util.HashSet;

import static main.utils.Constants.hashTable;

public class WordBreak {

    HashSet<String> dictionary;
    HashSet<String> result;

    public WordBreak(HashSet<String> dictionary) {
        this.dictionary = dictionary;
    }

    public HashSet<String> wordBreak(String word) {
        result = new HashSet<>();
        wordBreakUtil(word, word.length(), "", 0, false);
        return result;
    }

    /*
     * @param word: possible word
     *      result: intermediate result
     *      start : to start breaking the word from index start
     * an utility function break the word such that each part exist
     * in dictionary
     */

    void wordBreakUtil(String word, int n, String result, int start, boolean last) {
        if (start == n - 1 && !last) {
            String temp = result + get(word.substring(start));
            this.result.add(temp);
        } else if (!last) {
            String temp = result + get(word.substring(start, start + 1)) + "-";
            wordBreakUtil(word, n, temp, start + 1, true);
        }
        for (int i = start + 1; i <= n; i++) {
            String prefix = word.substring(start, i);
            if (dictionary.contains(prefix)) {
                if (i == n) {
                    result += prefix;
                    this.result.add(result);
                    return;
                }
                wordBreakUtil(word, n, result + prefix + "-", i, false);
            }
        }
    }

    /*
     * @param x: x is 0 or 1 or a character
     * returns the corresponding number of a character
     */
    int get(String x) {
        if (x.equals("0"))
            return 0;
        if (x.equals("1"))
            return 1;
        for (int i = 2; i <= 9; i++)
            if (hashTable[i].contains(x))
                return i;
        return 0;
    }
}
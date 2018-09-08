package test.utils;

import main.utils.PossibleWords;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

public class PossibleWordsTest {
    public PossibleWords possibleWords;

    @Before
    public void init() {
        possibleWords = new PossibleWords();
    }

    @Test
    public void test1() {
        HashSet<String> words = possibleWords.getWords("123");
        Assert.assertEquals(9, words.size());
    }

    @Test
    public void test2(){
        HashSet<String > words=possibleWords.getWords("");
        Assert.assertEquals(true, words.contains(""));
    }
    @Test
    public void test3(){
        HashSet<String> words=possibleWords.getWords("1024");
        Assert.assertEquals(9,words.size());
    }
}
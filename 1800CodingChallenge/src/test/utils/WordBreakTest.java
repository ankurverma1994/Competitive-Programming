package test.utils;

import main.utils.WordBreak;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

public class WordBreakTest {
    WordBreak wordBreak;

    @Before
    public void init() {
        HashSet<String> dic = new HashSet<>();
        dic.add("CALL");
        dic.add("ME");
        dic.add("CAT");
        dic.add("DOG");
        dic.add("PIT");
        dic.add("TIME");
        wordBreak = new WordBreak(dic);
    }

    @Test
    public void test1() {
        HashSet<String> result = wordBreak.wordBreak("PITIME");
        Assert.assertEquals(1, result.size());
        Assert.assertEquals("[PIT-4-ME]", result.toString());
    }
    @Test
    public void test2(){
        HashSet<String> result=wordBreak.wordBreak("PITTIME");
        Assert.assertEquals(1,result.size());
    }
    @Test
    public void test3(){
        HashSet<String> result=wordBreak.wordBreak("CALLED");
        Assert.assertEquals(0,result.size());
    }
    @Test
    public void test4(){
        HashSet<String> result=wordBreak.wordBreak("0CALL0");
        Assert.assertEquals(1,result.size());
    }
}
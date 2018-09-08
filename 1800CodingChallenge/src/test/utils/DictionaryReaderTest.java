package test.utils;

import main.exception.FileReading;
import main.utils.DictionaryReader;
import org.junit.Assert;
import org.junit.Test;
import test.ConstantTest;

import java.util.HashSet;


public class DictionaryReaderTest {
    private DictionaryReader reader;

    @Test(expected = FileReading.class)
    public void processDictionaryError() throws FileReading {
        reader = new DictionaryReader(ConstantTest.incorrectDicLocation);
        reader.processDictionary();
    }

    @Test
    public void processDictionaryTest() throws FileReading {
        reader = new DictionaryReader();
        HashSet<String> dic = reader.processDictionary();
        Assert.assertEquals(dic.size(), 4);
    }

    @Test
    public void processDictionaryTest1() throws FileReading {
        reader = new DictionaryReader(ConstantTest.correctDicLocation);
        HashSet<String> dic = reader.processDictionary();
        Assert.assertEquals(dic.size(), 4);
    }
}
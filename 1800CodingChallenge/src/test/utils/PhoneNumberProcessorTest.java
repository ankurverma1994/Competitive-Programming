package test.utils;

import main.exception.FileReading;
import main.utils.PhoneNumberProcessor;
import org.junit.Assert;
import org.junit.Test;
import test.ConstantTest;

import java.util.HashSet;

public class PhoneNumberProcessorTest {
    PhoneNumberProcessor phoneNumberProcessor;

    @Test(expected = FileReading.class)
    public void test() throws FileReading {
        String location = ConstantTest.incorrectPhoneLocation;
        HashSet<String> dictionary = new HashSet<>();
        dictionary.add("ACONEX");
        dictionary.add("CODING");
        phoneNumberProcessor = new PhoneNumberProcessor(location, dictionary);
        phoneNumberProcessor.fromFile();
    }

    @Test
    public void test1() {
        String location = ConstantTest.correctPhoneLocation;
        HashSet<String> dictionary = new HashSet<>();
        dictionary.add("ACONEX");
        dictionary.add("CALL");
        dictionary.add("ME");
        phoneNumberProcessor = new PhoneNumberProcessor(location, dictionary);
        System.out.println("pass");
    }
}
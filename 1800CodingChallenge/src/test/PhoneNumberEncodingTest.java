package test;

import main.PhoneNumberEncoding;
import main.exception.FileReading;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhoneNumberEncodingTest {
    PhoneNumberEncoding phoneNumberEncoding;

    @Before
    public void init() {
        phoneNumberEncoding = new PhoneNumberEncoding();
    }

    /*
     * Invalid argument passed error
     */
    @Test(expected = FileReading.class)
    public void testMain1() throws FileReading {
        String args[] = {"phone.txt", "-d", "dic.txt"};
        phoneNumberEncoding.main(args);
    }

    /*
     * This would produce error by reading phone number because directory
     * doesnot exist
     */
    @Test(expected = FileReading.class)
    public void testMain2() throws FileReading {
        String args[] = {"-d", "dic.txt", "phone.txt"};
        phoneNumberEncoding.main(args);
    }

    /*
     * default dictionary is set
     * phone number location invalid
     */
    @Test(expected = FileReading.class)
    public void testMain3() throws FileReading {
        String args[] = {"phone.txt"};
        phoneNumberEncoding.main(args);
    }
    @Test
    public void testMain4()throws FileReading{
        String args[]={ConstantTest.correctPhoneLocation};
        phoneNumberEncoding.main(args);
        System.out.println("PASSED");
    }
}
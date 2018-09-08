package main.utils;

import main.exception.FileReading;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class PhoneNumberProcessor {
    public boolean STDIN;
    String location;
    private Result result;
    BufferedReader buffer;

    public PhoneNumberProcessor(HashSet<String> dictionary) {
        this.STDIN = true;
        result = new Result(dictionary);
    }

    public PhoneNumberProcessor(String location, HashSet<String> dictionary) {
        this.location = location;
        result = new Result(dictionary);
    }

    /*
     * Read phone number from File, validates it
     * and process the number to get result
     */
    public void fromFile() throws FileReading {
        String tempString;
        try {
            buffer = new BufferedReader(new FileReader(location));
            while ((tempString = buffer.readLine()) != null) {
                String number = getNumber(tempString);
                result.process(tempString, number);
            }

        } catch (Exception e) {
            throw new FileReading(Constants.phoneReadError);
        }
    }

    /*
     * Read phone number from Standard Input, validates it
     * and process the number to get result
     */
    public void consoleInput() throws FileReading {
        System.out.println(Constants.startGettingInput);
        buffer = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String temp;
            try {
                temp = buffer.readLine();
            } catch (IOException e) {
                throw new FileReading(Constants.IOException);
            }
            temp = temp.trim();
            temp = temp.toUpperCase();
            if (temp.equals(Constants.EXIT)) break;
            String number = getNumber(temp);
            result.process(temp, number);
        }
    }

    /*
     * Removes all whitespace and punctuation from the provided text.
     */
    String getNumber(String number) {
        String result = "";
        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (isDigit(ch))
                result += ch;
        }
        return result;
    }

    /*
     * @param c: character to validate is digit
     * returns true if given character is digit
     */
    boolean isDigit(char c) {
        return '0' <= c && c <= '9';
    }
}
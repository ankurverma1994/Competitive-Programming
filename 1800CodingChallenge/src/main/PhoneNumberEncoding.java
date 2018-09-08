package main;


import main.exception.FileReading;
import main.utils.Constants;
import main.utils.DictionaryReader;
import main.utils.PhoneNumberProcessor;

import java.util.HashSet;
/*
 * Description : This is the main class of the 1800CodingChallenge. This class
 * validates the command line user inputs and then call the other utility
 * classes to produce the required output to the user.
 */

public class PhoneNumberEncoding {

    //Utility classes used to process the data files.
    static DictionaryReader dictionaryReader;
    static PhoneNumberProcessor phoneNumberProcessor;
    static boolean valid;
    static HashSet<String> dictionary;

    public static void main(String[] args) throws FileReading {
        // Validating the command line arguments as well as setting the
        // -d parameter, if it's provided.
        valid = false;
        initDictionary(args);
        if (!valid) {
            throw new FileReading(Constants.inputFormat);
        }
        dictionary = dictionaryReader.processDictionary();
        initPhoneNumber(args);
        if (!valid) {
            throw new FileReading(Constants.inputFormat);
        }
        if (phoneNumberProcessor.STDIN) phoneNumberProcessor.consoleInput();
        else phoneNumberProcessor.fromFile();
    }

    /*
     * Initialises dictionary if -d parameter is given else
     * default dictionary is setup
     */
    public static void initDictionary(String args[]) {
        if (args.length == 0 || args.length == 1) {
            dictionaryReader = new DictionaryReader();
            valid = true;
        }
        if (args.length == 3 || args.length == 2) {
            if (args[0].equals(Constants.dictionarySymbol)) {
                dictionaryReader = new DictionaryReader(args[1]);
                valid = true;
            }
        }
    }

    /*
     * Setup phone number location of file is provided else
     * Input is taken from Standard Input
     */
    public static void initPhoneNumber(String args[]) {
        if (args.length == 0 || args.length == 2) {
            phoneNumberProcessor = new PhoneNumberProcessor(dictionary);
            valid = true;
        }
        if (args.length == 1) {
            valid = true;
            phoneNumberProcessor = new PhoneNumberProcessor(args[0], dictionary);
        }
        if (args.length == 3) {
            valid = true;
            phoneNumberProcessor = new PhoneNumberProcessor(args[2], dictionary);
        }
    }
}
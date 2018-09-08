package main.utils;

public class Constants {
    public final static String[] hashTable = {"", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};

    public final static String dictionaryReadError = "Error while reading Dictionary";

    public final static String phoneReadError = "Error while reading Phone Number";

    public final static String dictionarySymbol = "-d";

    public final static String inputFormat = "You have option to specify dictionary file using \"-d <dictionary_location>\" command" +
            "+\n If dictionary is not specified default dictionary is set." +
            "\n You have an option to specify phone number list location as command line argument" +
            "\n If phone number location not specified then you can input phone number through STDIN";

    public final static String skipped = "Phone Number skipped" +
            "\n Possible Reason: No words in dictionary found OR two consecutive digits were being unchanged";

    public final static String IOException = "Error while reading phone number";

    public final static String EXIT = "EXIT";

    public final static String startGettingInput = "Since no phone number directory has been specified. Enter phone number from STDIN" +
            "+\n Type EXIT to stop the program";

}
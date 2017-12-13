package com.xebia.headerbuddy.utilities;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;

import java.util.*;

public abstract class APIKeyGenerator {

    public static final int KEY_LENGTH = 23;

    public static final String allowedLowercaseChars = "abcdefghijklmnopqrstuvwxyz";
    public static final String allowedUppercaseChars = WordUtils.capitalize(allowedLowercaseChars);
    public static final String allowedNumericChars = "0123456789";

    public static final String allowedChars = allowedLowercaseChars + allowedUppercaseChars + allowedNumericChars;

    public static String generate() {
        StringBuilder keyToReturn = new StringBuilder();

        // Create char array of allowedChars string
        char[] allowedCharsArray = allowedChars.toCharArray();

        // Convert charArray to List
        List<Character> allowedCharsList = toCharList(allowedCharsArray);

        // Shuffle list
        Collections.shuffle(allowedCharsList);

        // Create random instance
        Random random = new Random();

        for (int i = 0; i < KEY_LENGTH; i++){
            // Get random index
            int randomIndex = random.nextInt(allowedCharsList.size());

            // Fetch character
            char toAdd = allowedCharsList.get(randomIndex);

            // add character to key
            keyToReturn.append(toAdd);
        }

        System.out.println(keyToReturn);

        return keyToReturn.toString();
    }

    private static List<Character> toCharList(char[] charArray){
        // Convert to List
        List<Character> charList = new ArrayList<>();
        for(char c : charArray){
            charList.add(c);
        }

        return charList;
    }
}
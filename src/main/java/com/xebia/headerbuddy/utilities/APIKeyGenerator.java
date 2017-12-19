package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.entities.Euser;
import com.xebia.headerbuddy.models.entities.repositories.EuserRepository;
import org.apache.commons.lang.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

@Component
public class APIKeyGenerator {

    @Autowired
    private EuserRepository userRepository;

    public final int KEY_LENGTH = 23;

    public final String allowedLowercaseChars = "abcdefghijklmnopqrstuvwxyz";
    public final String allowedUppercaseChars = WordUtils.capitalize(allowedLowercaseChars);
    public final String allowedNumericChars = "0123456789";

    public String getKey(){
        String APIKey = generateKey();

        if(checkDatabaseForExistingKey("abc"))
            return "ben hier";

        return APIKey;
    }

    private String generateKey() {
        // StringBuilder for api key
        StringBuilder keyToReturn = new StringBuilder();

        // Create List of all allowed characters
        String allowedChars = allowedLowercaseChars + allowedUppercaseChars + allowedNumericChars;
        char[] allowedCharsArray = allowedChars.toCharArray();
        List<Character> allowedCharsList = toCharList(allowedCharsArray);

        // Shuffle list
        Collections.shuffle(allowedCharsList);

        // Create random instance
        Random random = new Random();

        for (int i = 0; i < KEY_LENGTH; i++){
            // Add a random character to the key
            int randomIndex = random.nextInt(allowedCharsList.size());
            char toAdd = allowedCharsList.get(randomIndex);
            keyToReturn.append(toAdd);
        }

        return keyToReturn.toString();
    }

    private boolean checkDatabaseForExistingKey(String apiKey){
        Iterable<Euser> users = userRepository.findUserByEmail("a@a.a");

        return true;
    }

    private List<Character> toCharList(char[] charArray){
        // Convert CharArray to List
        List<Character> charList = new ArrayList<>();
        for(char c : charArray){
            charList.add(c);
        }

        return charList;
    }
}
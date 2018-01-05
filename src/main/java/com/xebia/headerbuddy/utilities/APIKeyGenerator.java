package com.xebia.headerbuddy.utilities;

import com.xebia.headerbuddy.models.ApiKey;
import com.xebia.headerbuddy.models.entities.Euser;
import com.xebia.headerbuddy.models.entities.repositories.EuserRepository;
import org.apache.commons.lang.WordUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public abstract class APIKeyGenerator {

    public static final int KEY_LENGTH = 23;
    public static final String allowedLowercaseChars = "abcdefghijklmnopqrstuvwxyz";
    public static final String allowedUppercaseChars = WordUtils.capitalize(allowedLowercaseChars);
    public static final String allowedNumericChars = "0123456789";
    private static final int MAX_ATTEMPTS = 50;

    public static ApiKey getKey(EuserRepository userRepository, String email) throws Exception {
        // Check if a user with this email already has requested a key
        Euser user = userRepository.findByEmail(email);
        if (user != null) {
            // throw exception if this user already exists
            throw new Exception("An API key with this email address already exists!");
        }

        // Try to create a key (fails after 50 times)
        for (int attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
            // Create key
            String apiKey = generateKey();

            // if no user exists with this key the key is valid.
            // save a new user to the db and return the key, else try again
            if (!userExistsWithApiKey(userRepository, apiKey)) {
                // Create new user
                Euser newUser = new Euser(apiKey, email);
                newUser.setCreationdate(new Date());

                // Save the user
                userRepository.save(newUser);

                // Return the key
                return new ApiKey(apiKey);
            }
        }

        throw new Exception("Can't create API key right now! please try again later.");
    }

    public static String generateKey() {
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

        for (int i = 0; i < KEY_LENGTH; i++) {
            // Add a random character to the key
            int randomIndex = random.nextInt(allowedCharsList.size());
            char toAdd = allowedCharsList.get(randomIndex);
            keyToReturn.append(toAdd);
        }

        return keyToReturn.toString();
    }

    private static boolean userExistsWithApiKey(EuserRepository userRepository, String apiKey) {
        // Get user with this API key
        Euser user = userRepository.findByApikey(apiKey);

        // If a user exists with this key, return true
        if (user != null) {
            return true;
        }

        // else return false
        return false;
    }

    private static List<Character> toCharList(char[] charArray) {
        // Convert CharArray to List
        List<Character> charList = new ArrayList<>();
        for (char c : charArray) {
            charList.add(c);
        }

        return charList;
    }
}

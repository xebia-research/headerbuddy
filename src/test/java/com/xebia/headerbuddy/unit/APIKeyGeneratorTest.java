package com.xebia.headerbuddy.unit;

import org.junit.Assert;
import org.junit.Test;
import com.xebia.headerbuddy.utilities.APIKeyGenerator;

import java.util.regex.Pattern;

public class APIKeyGeneratorTest {

    String APIKey;
    APIKeyGenerator keyGen = new APIKeyGenerator();

    @Test
    public void shouldNotReturnEmptyString(){
        // Arrange

        // Act
        APIKey = keyGen.getKey();

        // Assert
        Assert.assertNotEquals("", APIKey);
    }

    @Test
    public void shouldReturnStringOfDefinedLength(){
        // Arrange
        int keyLength = keyGen.KEY_LENGTH;

        // Act
        APIKey = keyGen.getKey();

        // Assert
        Assert.assertTrue(APIKey.length() == keyLength);
    }

    @Test
    public void shouldReturnStringWithoutIllegalCharacters(){
        // Arrange
        String Regex = "(?i)^[a-z0-9]+$";

        // Act
        APIKey = keyGen.getKey();

        // Assert
        Assert.assertTrue(Pattern.matches(Regex, APIKey));
    }
}

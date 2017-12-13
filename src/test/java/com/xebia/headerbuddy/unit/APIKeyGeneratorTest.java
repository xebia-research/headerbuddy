package com.xebia.headerbuddy.unit;

import org.junit.Assert;
import org.junit.Test;
import com.xebia.headerbuddy.utilities.APIKeyGenerator;

import java.util.regex.Pattern;

public class APIKeyGeneratorTest {

    @Test
    public void shouldNotReturnEmptyString(){
        // Arrange
        String APIKey;

        // Act
        APIKey = APIKeyGenerator.generate();

        // Assert
        Assert.assertNotEquals("", APIKey);
    }

    @Test
    public void shouldReturnStringOfDefinedLength(){
        // Arrange
        String APIKey;
        int keyLength = APIKeyGenerator.KEY_LENGTH;

        // Act
        APIKey = APIKeyGenerator.generate();

        // Assert
        Assert.assertTrue(APIKey.length() == keyLength);
    }

    @Test
    public void shouldReturnStringWithoutIllegalCharacters(){
        // Arrange
        String APIKey;
        String Regex = "(?i)^[a-z0-9]+$";

        // Act
        APIKey = APIKeyGenerator.generate();

        // Assert
        Assert.assertTrue(Pattern.matches(Regex, APIKey));
    }
}

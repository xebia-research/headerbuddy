package com.xebia.headerbuddy.unit;

import org.junit.Assert;
import org.junit.Test;
import com.xebia.headerbuddy.utilities.APIKeyGenerator;

import java.util.regex.Pattern;

public class APIKeyGeneratorTest {

    String APIKey;

    @Test
    public void shouldNotReturnEmptyString(){
        // Arrange

        // Act
        APIKey = APIKeyGenerator.generateKey();

        // Assert
        Assert.assertNotEquals("", APIKey);
    }

    @Test
    public void shouldReturnStringOfDefinedLength(){
        // Arrange
        int keyLength = APIKeyGenerator.KEY_LENGTH;

        // Act
        APIKey = APIKeyGenerator.generateKey();

        // Assert
        Assert.assertTrue(APIKey.length() == keyLength);
    }

    @Test
    public void shouldReturnStringWithoutIllegalCharacters(){
        // Arrange
        String Regex = "(?i)^[a-z0-9]+$";

        // Act
        APIKey = APIKeyGenerator.generateKey();

        // Assert
        Assert.assertTrue(Pattern.matches(Regex, APIKey));
    }
}

package com.xebia.headerbuddy.e2e;

import com.xebia.headerbuddy.models.entities.Euser;
import com.xebia.headerbuddy.models.entities.repositories.EuserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.Date;

// for the annotations
@RunWith(SpringRunner.class)
// Run spring on a random port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeaderBuddyUrlParamTest {

    @Autowired
    private TestRestTemplate template;
    @Autowired
    private EuserRepository userRepository;

    private String urlNoExtension = "http://andonoz";
    private String urlNoProtocol = "www.andonoz.com";
    private String urlCorrect = "http://www.andonoz.com";

    // Add the user for the api key
    @Before
    public void init(){
        Euser u = new Euser("abc", "m@m.nl");
        u.setCreationdate(new Date());
        userRepository.save(u);
    }

    @Test
    public void HeaderBuddyURLNoExtensionTest() {
        String url = "/headerbuddy/api?output=xml&key=abc&url="+urlNoExtension;

        ResponseEntity<String> response = template.postForEntity(url, "", String.class);
        Assert.assertTrue("Response code should be 400 (No extension)", response.getStatusCode().is4xxClientError());
    }

    @Test
    public void HeaderBuddyURLNoProtocolTest() {
        String url = "/headerbuddy/api?output=xml&key=abc&url="+urlNoProtocol;

        ResponseEntity<String> response = template.postForEntity(url, "", String.class);
        Assert.assertTrue("Response code should be 400 (No protocol)", response.getStatusCode().is4xxClientError());
    }

    @Test
    public void HeaderBuddyURLCorrectTest() {
        String url = "/headerbuddy/api?output=xml&key=abc&url="+urlCorrect;

        ResponseEntity<String> response = template.postForEntity(url, "", String.class);

        Assert.assertTrue("Response code should be 200 (Correct url)", response.getStatusCode().is2xxSuccessful());
    }
}

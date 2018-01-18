package com.xebia.headerbuddy.e2e;

import com.xebia.headerbuddy.models.entities.Euser;
import com.xebia.headerbuddy.models.entities.repositories.EuserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

// for the annotations
@RunWith(SpringRunner.class)
// Run spring on a random port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeaderBuddyApiKeyTest {

    @Autowired
    private TestRestTemplate template;
    @Autowired
    private EuserRepository userRepository;

    private String key = "abc";
    private String email = "m@m.nl";

    @Value("${test.http.url}")
    private String testedUrl;

    // Add the user for the api key
    @Before
    public void init(){
        Euser u = new Euser(key, email);
        u.setCreationdate(new Date());
        userRepository.save(u);
    }

    @Test
    public void HeaderBuddyCorrectApiKeyTest() {
        String url = "/headerbuddy/api?key="+ key +"&url=" + testedUrl;

        ResponseEntity<String> response = template.postForEntity(url, "", String.class);
        Assert.assertTrue("Response code should be 200 (Correct api key)", response.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void HeaderBuddyWrongApiKeyTest() {
        String url = "/headerbuddy/api?key=wrong&url=" + testedUrl;

        ResponseEntity<String> response = template.postForEntity(url, "", String.class);
        Assert.assertTrue("Response code should be 400 (Wrong api key; The test only works if api key is enabled)", response.getStatusCode().is4xxClientError());
    }
}

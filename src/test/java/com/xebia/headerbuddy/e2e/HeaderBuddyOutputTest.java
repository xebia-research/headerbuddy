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
public class HeaderBuddyOutputTest {

    @Autowired
    private TestRestTemplate template;
    @Autowired
    private EuserRepository userRepository;

    @Value("${test.http.url}")
    private String testedUrl;

    // Add the user for the api key
    @Before
    public void init(){
        Euser u = new Euser("abc", "m@m.nl");
        u.setCreationdate(new Date());
        userRepository.save(u);
    }

    @Test
    public void undefinedOutputShouldReturnJson() {
        String url = "/headerbuddy/api?key=abc&url="+testedUrl;

        ResponseEntity<String> response = template.postForEntity(url,"", String.class);
        Assert.assertTrue("Content type should be json", response.getHeaders().getContentType().toString().contains("application/json"));
    }

    @Test
    public void jsonOutputShouldReturnJson() {
        String url = "/headerbuddy/api?key=abc&url="+testedUrl;

        ResponseEntity<String> response = template.postForEntity(url, "", String.class);
        Assert.assertTrue("Content type should be json", response.getHeaders().getContentType().toString().contains("application/json"));
    }

    @Test
    public void xmlOutputShouldReturnXml() {
        String url = "/headerbuddy/api?key=abc&url="+testedUrl+"&output=xml";

        ResponseEntity<String> response = template.postForEntity(url, "", String.class);
        Assert.assertTrue("Content type should be xml", response.getHeaders().getContentType().toString().contains("application/xml"));
    }

    @Test
    public void htmlOutputShouldReturnHtml() {
        String url = "/headerbuddy/api?key=abc&url="+testedUrl+"&output=html";

        ResponseEntity<String> response = template.postForEntity(url, "", String.class);
        Assert.assertTrue("Content type should be xml", response.getHeaders().getContentType().toString().contains("text/html"));
    }

    @Test
    public void wrongOutputShouldReturnError() {
        String url = "/headerbuddy/api?key=abc&url="+testedUrl+"&output=wrong";

        ResponseEntity<String> response = template.postForEntity(url, "", String.class);
        Assert.assertTrue("Response code should be 400 (wrong output)", response.getStatusCode().is4xxClientError());
    }
}

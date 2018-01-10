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

    // Get the random port spring is running on
    @Value("${local.server.port}")
    private int port;

    // Add the user for the api key
    @Before
    public void init(){
        Euser u = new Euser("abc", "m@m.nl");
        u.setCreationdate(new Date());
        userRepository.save(u);
    }

    @Test
    public void HeaderBuddyOutputJsonTest() {
        String url = "http://localhost:"+port+"/headerbuddy/api?key=abc&url="+testedUrl;

        ResponseEntity<String> jsonResponse = template.getForEntity(url + "output=json", String.class);
        Assert.assertTrue("Response code should be 200 (Json response)", jsonResponse.getStatusCode().is2xxSuccessful());
        Assert.assertTrue("Content type should be json", jsonResponse.getHeaders().getContentType().toString().equals("application/json;charset=UTF-8"));
    }

    @Test
    public void HeaderBuddyOutputXmlTest() {
        String url = "http://localhost:"+port+"/headerbuddy/api?output=xml&key=abc&url="+testedUrl;

        ResponseEntity<String> xmlResponse = template.getForEntity(url, String.class);
        Assert.assertTrue("Response code should be 200 (Xml response)", xmlResponse.getStatusCode().is2xxSuccessful());
        Assert.assertTrue("Content type should be xml", xmlResponse.getHeaders().getContentType().toString().equals("application/xml;charset=UTF-8"));
    }
}

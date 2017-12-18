package com.xebia.headerbuddy.e2e;

import com.xebia.headerbuddy.controllers.HeaderBuddyController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//so the annotations won't be ignored
@RunWith(SpringRunner.class)
//auto-configure the Spring MVC infrastructure and limit the scanned beans this is purely for the main controller(s)
@WebMvcTest(HeaderBuddyController.class)
public class HeaderBuddyUrlParamTest {

    //auto injects the class
    @Autowired
    private MockMvc mvc;

    private String urlNoExtension = "http://andonoz";
    private String urlNoProtocol = "www.andonoz.com";
    private String urlFullyCorrect = "http://www.andonoz.com";
    private String urlCorrect = "http://www.andonoz.com";

    //Test for checking if the url validator works
    @Test
    public void HeaderBuddyURLTest() throws Exception {
        this.mvc.perform(get("/headerbuddy/api?key=123&url="+urlNoExtension).accept(MediaType.APPLICATION_JSON))
                //response is status code 400
                .andExpect(status().isBadRequest())
                //expected response type is json
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                //expected url in the url field is the tested url
                .andExpect(jsonPath("$.error").value("Invalid URL!"));
        this.mvc.perform(get("/headerbuddy/api?key=123&url="+urlNoProtocol).accept(MediaType.APPLICATION_JSON))
                //response is status code 400
                .andExpect(status().isBadRequest())
                //expected response type is json
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                //expected url in the url field is the tested url
                .andExpect(jsonPath("$.error").value("Invalid URL!"));
        this.mvc.perform(get("/headerbuddy/api?key=123&url="+urlFullyCorrect).accept(MediaType.APPLICATION_JSON))
                //response is status code 200
                .andExpect(status().isOk())
                //expected response type is json
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                //expected url in the url field is the tested url
                .andExpect(jsonPath("$.url").value(urlFullyCorrect));
        this.mvc.perform(get("/headerbuddy/api?key=123&url="+urlCorrect).accept(MediaType.APPLICATION_JSON))
                //response is status code 200
                .andExpect(status().isOk())
                //expected response type is json
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                //expected url in the url field is the tested url
                .andExpect(jsonPath("$.url").value(urlCorrect));
    }
}

package com.xebia.headerbuddy.e2e;

import com.xebia.headerbuddy.controllers.HeaderBuddyController;
import com.xebia.headerbuddy.models.entities.repositories.EheaderRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class HeaderBuddyControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private EheaderRepository headerRepository;

    private String testedUrl = "http://andonoz.com";

    //Test for checking if there is a response with the given url (tested url)
    //in any case if the response type is in json there should be a url field with the given url
    @Test
    public void HeaderBuddyControllerTest() throws Exception {
        this.mvc.perform(get("/headerbuddy/api?key=123&url="+testedUrl))
                //response is status code 200
                .andExpect(status().isOk())
                //expected response type is json
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                //expected url in the url field is the tested url
                .andExpect(jsonPath("$.url").value(testedUrl));
    }
}

package com.xebia.headerbuddy.unit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//This will make the test run on a random available port
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HeaderbuddyApplicationTest
{

	//restTemplate so we can resolve links at the running server
	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	public void testHelloWorld()
	{
		String body = this.restTemplate.getForObject("/helloworld", String.class);
		assertThat(body).isEqualTo("{\"id\":1,\"greeting\":\"Hello World\"}");
	}

}

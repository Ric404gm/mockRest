package com.testmock.mockservices;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.MockServerConfigurer;
import org.springframework.test.web.reactive.server.WebTestClient.MockServerSpec;
import org.springframework.test.web.servlet.MockMvc;

import com.testmock.mockservices.configuration.MockServerRest;
import com.testmock.mockservices.service.TestService;


import org.springframework.util.Assert;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MockservicesApplicationTests {

	private static final Logger LOGGER = org.slf4j.LoggerFactory.getLogger(MockservicesApplication.class);

	@Autowired
	private MockMvc mockMvc; // For MockTestRest Services

	private MockServerRest mockServerRest;

	@Autowired
	private TestService service;



	@BeforeAll
	public void startServer(){

		LOGGER.info(" Start MockServerRest  ");
		mockServerRest = new MockServerRest();
		mockServerRest.whenMockServer();
	}

	

	/*
	 * Example if needs mock mvc 
	 * @Test
			void shouldReturnSuccessfulResponseOnValidRequest() throws Exception {
				MvcResult result = mockMvc.perform(
								get("/demo")
								.contentType(MediaType.APPLICATION_JSON)
						)
						.andExpect(status().isOk())
						.andReturn();
				String actualResponse = result
		.getResponse()
		.getContentAsString();
				assertThat(actualResponse).isEqualToIgnoringWhitespace("Mocked Response!!!");

			}
	 */
 
	@Test
	@DisplayName("  * Try Mock Service")
	void  whenMockRestEndPoint_thenCallFromService_verifyResultsWithMathResponse() throws Exception {
		String resultService = this.service.processContentResponseFromMockService();
		Assert.hasText(resultService,"Mocked Response!!!!!!");
		
	}


	@AfterEach
    public void verify() {
        mockServerRest.verifyMockServer();
    }
    @AfterAll
    public void stop() {
		mockServerRest.stopServer();
    }
}

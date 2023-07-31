package com.testmock.mockservices.configuration;

import java.util.concurrent.TimeUnit;

import org.mockserver.client.MockServerClient;
import org.mockserver.integration.ClientAndServer;
import org.mockserver.model.HttpRequest;
import org.mockserver.model.HttpResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


public class MockServerRest {



    private static final Integer MOCK_SERVER_PORT = 1081;
    private final ClientAndServer clientAndServer;
    private final MockServerClient mockServerClient = new MockServerClient("localhost", MOCK_SERVER_PORT);


    public MockServerRest(){
        this.clientAndServer =  ClientAndServer.startClientAndServer(MOCK_SERVER_PORT);
    }

    //Registrar el web endpoint 
    public void  whenMockServer(){

        mockServerClient
                .when(
                    HttpRequest.request()
                                .withMethod("GET")
                                .withPath("/api/helloWorld")
                )
                .respond(
                         HttpResponse.response()
                                .withStatusCode(200)
                                .withBody("Mocked Response!!!!!!")
                                //.withDelay(TimeUnit.SECONDS, 1)
                );
    }

    public void verifyMockServer(){
        mockServerClient.verify( HttpRequest.request()
            .withMethod(HttpMethod.GET.name())
                .withPath("/api/helloWorld") );
    }
    
    public void stopServer(){
        this.clientAndServer.stop();   
    }


  
        
    
}

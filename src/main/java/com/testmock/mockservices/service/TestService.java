package com.testmock.mockservices.service;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.netty.handler.codec.http.HttpMethod;

@Service
public class TestService {

    @Autowired
    private RestTemplate restTemplate;
    
    @Value("${api.url.helloWorld}")
    private String helloWorldURL;

    
    

    public String processContentResponseFromMockService(){

        /**
         * 
            RequestEntity<String> requestEntity = new HttpEntityBuilder<String>()
                    .withURL(helloWorldURL)
                    .withHttpMethod(HttpMethod.GET)
                    .build();
            String responseBody = restTemplate.exchange(requestEntity, String.class).getBody();
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
         */
        /* 
             RequestEntity<String> requestEntity = new RequestEntity<String>().get( new URI(helloWorldURL)).build();
            String responseBody = restTemplate.exchange(requestEntity, String.class).getBody();
            return responseBody;

             restTemplate
        */
        ResponseEntity<String> responseFromRest  = restTemplate.getForEntity(  helloWorldURL, String.class);

        return  responseFromRest.getBody();
    }

}

package org.basic.microservice;

import org.basic.microservice.dtos.LoginUserDto;
import org.basic.microservice.model.User;
import org.basic.microservice.response.LoginResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = "org.basic.microservice")
public class UserControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    void getCurrentUser()  {
        LoginUserDto dto = new LoginUserDto("rusho","rusho123");
        ResponseEntity<LoginResponse> response = restTemplate.postForEntity("http://localhost:" + port + "/auth/login",dto, LoginResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String token = response.getBody().getToken();
        assertThat(token).isNotBlank();


        String authorizationHeaderName = "Authorization";
        String authorizationHeaderValue = "Bearer " + token;

        HttpHeaders tokenHeaders = new HttpHeaders();
        tokenHeaders.set(authorizationHeaderName, authorizationHeaderValue);
        HttpEntity<User> requestWithToken = new HttpEntity<User>(tokenHeaders);
        ResponseEntity<User> resp = restTemplate.exchange("http://localhost:" + port + "/users/current", HttpMethod.GET, requestWithToken, User.class, this.port);
        assertThat(resp.getStatusCode()).isEqualTo(HttpStatus.OK);

    }
}

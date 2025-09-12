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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = "org.basic.microservice")
public class AuthenticationControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void authenticateWithJWT()  {

        LoginUserDto dto = new LoginUserDto("rusho","rusho123");
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<User> request = new HttpEntity<User>(httpHeaders);

        ResponseEntity<LoginResponse> response = restTemplate.postForEntity("http://localhost:" + port + "/auth/login",dto, LoginResponse.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        String token = response.getBody().getToken();
        assertThat(token).isNotBlank();

    }


}

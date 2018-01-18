package com.altarix.crud;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CrudApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestServiceTest {


        private int port = 8090;

        TestRestTemplate restTemplate = new TestRestTemplate();

        HttpHeaders headers = new HttpHeaders();

        @Test
        public void testRetrieveStudentCourse() {

            HttpEntity<String> entity = new HttpEntity<String>(null, headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    createURLWithPort("/api/1"),
                    HttpMethod.GET, entity, String.class);
            String expected = "{cardId:1,code:1,date:1515614400000, name:Документ_1,kind:Инструкция}";


            JSONAssert.assertEquals(expected, response.getBody(), false);
        }

        private String createURLWithPort(String uri) {
            return "http://localhost:" + port + uri;
        }
}

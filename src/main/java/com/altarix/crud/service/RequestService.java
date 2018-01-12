package com.altarix.crud.service;

import com.altarix.crud.model.entity.Doc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Вячеслав on 25.10.2017.
 */
public interface RequestService {

    int PORT = 8080;
    String BASE_URL = "http://localhost:" + PORT + "/app-portal/api/";
    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    default String getSessionId() throws JsonProcessingException {
        Map<String, Object> requestBody = new HashMap();
        requestBody.put("username", "admin");
        requestBody.put("password", "admin");
        requestBody.put("locale", "ru");
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> httpEntity = new HttpEntity(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
        return new RestTemplate().postForObject(BASE_URL + "login", httpEntity, String.class);
    }

    void synchronizeData(Doc doc) throws JsonProcessingException;
}

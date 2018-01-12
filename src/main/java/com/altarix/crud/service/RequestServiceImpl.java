package com.altarix.crud.service;

import com.altarix.crud.model.entity.Doc;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Вячеслав on 25.10.2017.
 */
@Service("requestService")
public class RequestServiceImpl implements RequestService {

    @Override
    public void synchronizeData(Doc doc) throws JsonProcessingException {
        String sessionId = null;
        try {
            sessionId = getSessionId();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        JSONObject jsonArrayValues = new JSONObject();
        jsonArrayValues.put("id", "NEW-df$Doc");
        jsonArrayValues.put("number", String.valueOf(doc.getCode()));
        jsonArrayValues.put("comment", doc.getName());
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonArrayValues);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("commitInstances", jsonArray);
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        new RestTemplate().exchange(BASE_URL + "commit?s=" + sessionId, HttpMethod.POST, new HttpEntity(jsonObject.toString(), requestHeaders), String.class);
    }
}

package com.my.corp.handlers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.my.corp.configuration.SpringConfig;
import com.my.corp.dao.CVDaoPostgreSQL;
import com.my.corp.dao.Dao;
import com.my.corp.entity.CVEntity;
import com.my.corp.utils.Utils;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CVHandler implements IHandler {
    @Autowired
    @Qualifier("CVDaoPostgreSQL")
    private Dao dao;

    @Override
    public APIGatewayProxyResponseEvent handle(LinkedHashMap<String, Object> object) {
        HashMap<String, String> parameters = (HashMap<String, String>) object.get("queryStringParameters");
        CVEntity entity;
        String id = parameters.get("cvID");
        try {
            entity = dao.get("poliakovaleek", id);
        } catch (RuntimeException e) {
            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(404)
                    .withBody(String.format("CV with ID [%s] not found", id))
                    .withIsBase64Encoded(false);
        }
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withHeaders(Map.of("Access-Control-Allow-Origin", "*",
                        "Content-Type", "application/json"))
                .withBody(Utils.objectToJSON(entity))
                .withIsBase64Encoded(false);
    }
}

package com.my.corp;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.my.corp.configuration.SpringConfig;
import com.my.corp.dao.CVDao;
import com.my.corp.dao.Dao;
import com.my.corp.utils.Utils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class Handler {
    public APIGatewayProxyResponseEvent handle(LinkedHashMap<String, Object> object) {
        System.out.println("Class: " + object.getClass());
        System.out.println(Utils.objectToJSON(object));

        if (object.get("path").equals("/cvs")
                && object.get("httpMethod").equals("GET")) {
            HashMap<String, String> parameters = (HashMap<String, String>) object.get("queryStringParameters");
            if (Objects.isNull(parameters)) {
                System.out.println("Path: /cvs; Method: GET");
                Dao dao = new AnnotationConfigApplicationContext(SpringConfig.class)
                        .getBean("CVDao", CVDao.class);

                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(200)
                        .withBody(Utils.objectToJSON(dao.get("poliakovaleek", null)))
                        .withIsBase64Encoded(false);
            } else if (parameters.containsKey("cvID")) {
                Dao dao = new AnnotationConfigApplicationContext(SpringConfig.class)
                        .getBean("CVDaoPostgreSQL", CVDao.class);
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(200)
                        .withBody(Utils.objectToJSON(dao.get("poliakovaleek", null)))
                        .withIsBase64Encoded(false);
            } else {
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(400)
                        .withIsBase64Encoded(false)
                        .withBody("Unknown set of parameters");
            }

        }

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody("Lambda tests DONE")
                .withIsBase64Encoded(false);
    }
}

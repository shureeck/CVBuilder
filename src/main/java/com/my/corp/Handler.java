package com.my.corp;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.my.corp.configuration.SpringConfig;
import com.my.corp.dao.CVDao;
import com.my.corp.dao.Dao;
import com.my.corp.utils.Utils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.LinkedHashMap;

public class Handler {
    public APIGatewayProxyResponseEvent handle(LinkedHashMap<String, Object> object) {
        System.out.println("Class: " + object.getClass());
        System.out.println(Utils.objectToJSON(object));

        if (object.get("path").equals("/cvs")
                && object.get("httpMethod").equals("GET")) {
            System.out.println("Path: /cvs; Method: GET");
            Dao dao = new AnnotationConfigApplicationContext(SpringConfig.class)
                    .getBean("CVDao", CVDao.class);

            return new APIGatewayProxyResponseEvent()
                    .withStatusCode(200)
                    .withBody(Utils.objectToJSON(dao.get("poliakovaleek", null)))
                    .withIsBase64Encoded(false);

        }

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody("Lambda tests DONE")
                .withIsBase64Encoded(false);
    }
}

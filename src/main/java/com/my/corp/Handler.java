package com.my.corp;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.my.corp.configuration.SpringConfig;
import com.my.corp.dao.CVDao;
import com.my.corp.dao.CVDaoPostgreSQL;
import com.my.corp.dao.Dao;
import com.my.corp.handlers.CVHandler;
import com.my.corp.handlers.CVSHandler;
import com.my.corp.handlers.IHandler;
import com.my.corp.utils.Utils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class Handler {
    private IHandler handler;

    public APIGatewayProxyResponseEvent handle(LinkedHashMap<String, Object> object, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log(Utils.objectToJSON(object));
        if (object.get("path").equals("/cvs")
                && object.get("httpMethod").equals("GET")) {
            logger.log("Path: /cvs; Method: GET");

            HashMap<String, String> parameters = (HashMap<String, String>) object.get("queryStringParameters");

            if (Objects.isNull(parameters)) {
                handler = new AnnotationConfigApplicationContext(SpringConfig.class)
                        .getBean("CVSHandler", CVSHandler.class);
            } else if (parameters.containsKey("cvID")) {
                handler = new AnnotationConfigApplicationContext(SpringConfig.class)
                        .getBean("CVHandler", CVHandler.class);
            } else {
                return new APIGatewayProxyResponseEvent()
                        .withStatusCode(400)
                        .withHeaders(Map.of("Access-Control-Allow-Origin", "*",
                                "Content-Type", "application/json"))
                        .withIsBase64Encoded(false)
                        .withBody("Unknown set of parameters");
            }
            return handler.handle(object);
        }

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody("Lambda tests DONE")
                .withIsBase64Encoded(false);
    }
}

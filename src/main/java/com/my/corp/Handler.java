package com.my.corp;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.my.corp.utils.Utils;

import java.util.LinkedHashMap;

public class Handler {
    public APIGatewayProxyResponseEvent handle(LinkedHashMap<String,Object> object) {
        System.out.println("Class: " + object.getClass());
        System.out.println(Utils.objectToJSON(object));

        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody("Lambda tests DONE")
                .withIsBase64Encoded(false);
    }
}

package com.my.corp.handlers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.my.corp.dao.Dao;
import com.my.corp.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;

@Component
public class CVSHandler implements IHandler {
    @Autowired
    @Qualifier("CVDao")
    private Dao dao;

    @Override
    public APIGatewayProxyResponseEvent handle(LinkedHashMap<String, Object> object) {
        return new APIGatewayProxyResponseEvent()
                .withStatusCode(200)
                .withBody(Utils.objectToJSON(dao.get("poliakovaleek", null)))
                .withIsBase64Encoded(false);
    }
}

package com.my.corp.handlers;

import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import java.util.LinkedHashMap;

public interface IHandler {
    APIGatewayProxyResponseEvent handle(LinkedHashMap<String, Object> object);
}

package com.my.corp.utils;

import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import com.amazonaws.services.secretsmanager.model.GetSecretValueRequest;
import com.amazonaws.services.secretsmanager.model.GetSecretValueResult;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Log4j2
public class Utils {
    public static String objectToJSON(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T jsonToObject(String json, Class<T> object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(json, object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSecretValue(String secretName) {
        log.info(LoggerMessages.getMessage("read.secret", secretName));
        try {
            System.out.println(1);
            AWSSecretsManager manager = AWSSecretsManagerClientBuilder.standard().build();
            System.out.println(2);
            GetSecretValueRequest request = new GetSecretValueRequest().withSecretId(secretName);
            System.out.println(3);
            GetSecretValueResult result = manager.getSecretValue(request);
            return result.getSecretString();
        }catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return "NULL";
        }
    }
}

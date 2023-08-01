package com.my.corp.entity;

import com.my.corp.utils.Utils;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Objects;

@Component
public class Secret {
    private static Secret secret = null;
    private HashMap<String, String> secretValue;
    @Getter
    private String secretName = System.getenv("SECRET_NAME");
    private boolean valid = false;

    private Secret() {
        if (Objects.nonNull(secretName) && !secretName.isEmpty()) {
            System.out.println("Get secret:" + secretName);
            String secretString = Utils.getSecretValue(secretName);
            System.out.println(secretString);
            secretValue = Utils.jsonToObject(secretString, HashMap.class);
            valid = true;
        }
    }

    public static Secret getInstance() {
        if (Objects.isNull(secret)) {
            secret = new Secret();
        }
        return secret;
    }

    public String getValue(String key) {
        System.out.println("Get secret value: " + key);
        if (Objects.isNull(secretValue)) {
            return null;
        } else {
            return secretValue.get(key);
        }
    }

    public boolean isValid() {
        return valid;
    }
}

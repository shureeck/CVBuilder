package com.my.corp;

public class Handler {
    public String handle(){
        return "{\n" +
                "    \"statusCode\": 200,\n" +
                "    \"message\": \"Lambda Test\"\n" +
                "}";
    }
}

package com.my.corp;

import com.my.corp.utils.Utils;

public class Handler {
    public Object handle(Object object) {
        class Resp {
            public int statuSCode = 200;
            public String message = "Lambda tests DONE";
        }
        System.out.println("Class: " + object.getClass());
        System.out.println(Utils.objectToJSON(object));

        return Utils.objectToJSON(new Resp());
    }
}

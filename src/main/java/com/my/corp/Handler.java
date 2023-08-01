package com.my.corp;

import com.my.corp.utils.Utils;

public class Handler {
    public Object handle(Object object) {
        class Resp {
            final int statuSCode = 200;
            final String message = "Lambda tests DONE";
        }
        System.out.println("Class: " + object.getClass());
        System.out.println(Utils.objectToJSON(object));

        return new Resp();
    }
}

package com.ls.demo.utils;

import java.util.UUID;

public class IdUtils {

    public static String getUuid(){
        String id=UUID.randomUUID().toString();
        return id.replace("-","");
    }

}

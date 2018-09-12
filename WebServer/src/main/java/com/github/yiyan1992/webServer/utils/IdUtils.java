package com.github.yiyan1992.webServer.utils;

import java.util.UUID;

public class IdUtils {

    public static String getUuid(){
        String id=UUID.randomUUID().toString();
        return id.replace("-","");
    }

}

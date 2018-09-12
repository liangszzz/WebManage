package com.github.yiyan1992.webServer.entity.res;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER_INT)
public enum Code {

    SUCCESS(0),
    NOT_LOGIN_ERROR(1),
    PARAMETER_ERROR(2),
    NO_AUTHORITY(3),
    ALREADY_EXISTED(4),
    OTHER_ERROR(99);

    private int code;

    Code(int code) {
        this.code = code;
    }

}

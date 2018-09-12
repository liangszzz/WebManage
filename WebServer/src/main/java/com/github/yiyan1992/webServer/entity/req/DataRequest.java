package com.github.yiyan1992.webServer.entity.req;

import lombok.Data;

/**
 * 通用返回信息
 */
@Data
public class DataRequest {

    private String appid;

    private String sign;

    private String time;

    private String data;

}

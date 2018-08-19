package com.ls.demo.entity.res;

import lombok.Data;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 查询
 *
 * @param <T>
 */
@Data
public class DataResponse<T> implements Serializable {

    private DataResponse(){}

    public DataResponse(Code code) {
        this.code = code;
    }

    private static final DataResponse SUCCESS=new DataResponse(Code.SUCCESS);

    private Code code;
    private String msg="";
    private long count;
    private List<T> data;

    private Object entity;
    private Map<String, Object> dataMap;

    public static DataResponse of(Page page) {
        DataResponse dataResponse = new DataResponse<>();
        dataResponse.setCode(Code.SUCCESS);
        dataResponse.setMsg("");
        dataResponse.setCount(page.getTotalElements());
        dataResponse.setData(page.getContent());
        return dataResponse;
    }

    public static DataResponse of(List list) {
        DataResponse dataResponse = new DataResponse<>();
        dataResponse.setCode(Code.SUCCESS);
        dataResponse.setMsg("");
        dataResponse.setData(list);
        return dataResponse;
    }

    public static DataResponse of(Code code, Map<String, Object> map) {
        DataResponse dataResponse = new DataResponse<>();
        dataResponse.setCode(code);
        dataResponse.setDataMap(map);
        return dataResponse;
    }

    public static DataResponse of(Code code, Object entity, String msg) {
        DataResponse dataResponse = new DataResponse<>();
        dataResponse.setCode(code);
        dataResponse.setEntity(entity);
        dataResponse.setMsg(msg);
        return dataResponse;
    }

    public static DataResponse of(Code code, String msg) {
        DataResponse dataResponse = new DataResponse<>();
        dataResponse.setCode(code);
        dataResponse.setMsg(msg);
        return dataResponse;
    }

    public static DataResponse SUCCESS() {
        return SUCCESS;
    }

}

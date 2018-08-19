package com.ls.demo.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

public class ValidErrorGet {


    public static String getErrorFromBingingResult(BindingResult result) {
        List<ObjectError> errorList = result.getAllErrors();
        StringBuffer buffer=new StringBuffer();
        for (ObjectError error : errorList) {
            buffer.append(error.getDefaultMessage()).append("/n");
        }
        return buffer.toString();
    }
}

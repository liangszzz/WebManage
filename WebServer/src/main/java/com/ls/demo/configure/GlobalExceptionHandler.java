package com.ls.demo.configure;

import com.ls.demo.entity.res.Code;
import com.ls.demo.entity.res.DataResponse;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public DataResponse defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        return DataResponse.of(Code.ALREADY_EXISTED, "已存在的信息,请勿重复提交!");
    }
}

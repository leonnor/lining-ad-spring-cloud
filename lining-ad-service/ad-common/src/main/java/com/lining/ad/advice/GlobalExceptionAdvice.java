package com.lining.ad.advice;

import com.lining.ad.exception.AdException;
import com.lining.ad.vo.CommonResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * className GlobalExceptionAdvice
 * description TODO
 *
 * @author ln
 * @version 1.0
 * @date 2019/3/12 20:08
 */
@RestControllerAdvice
public class GlobalExceptionAdvice {

    @ExceptionHandler(value = AdException.class) //指定要处理的异常类
    public CommonResponse<String> handlerAdException(HttpServletRequest request, AdException ex){
        CommonResponse<String> response = new CommonResponse<>(-1, "business error");
        response.setData(ex.getMessage());
        return response;
    }
}

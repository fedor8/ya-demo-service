package com.example.demo.config;

import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by fedor.dydykin on 16.11.2019.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandlerController {

  @ExceptionHandler({Exception.class})
  @ResponseBody
  protected ResponseEntity<Object> handleInvalidRequest(Exception e, WebRequest request){
    log.error(e.getMessage(), e);
    var result = new HashMap<String, Object>();
    result.put("error", e.getClass().getCanonicalName());
    result.put("message", e.getMessage());
    return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}

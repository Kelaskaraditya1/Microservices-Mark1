package com.StarkIndustries.HotelMicroService.HotelMicroservice.exceptions;

import com.StarkIndustries.HotelMicroService.HotelMicroservice.keys.Keys;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String,Object> body = new LinkedHashMap<String,Object>();

        body.put(Keys.TIME_STAMP, LocalDateTime.now());
        body.put(Keys.STATUS,status);

        List<String> error = ex
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(errors->
                    errors.getDefaultMessage())
                .toList();

        body.put(Keys.ERROR,error);

        return new ResponseEntity<Object>(body,status);
    }
}

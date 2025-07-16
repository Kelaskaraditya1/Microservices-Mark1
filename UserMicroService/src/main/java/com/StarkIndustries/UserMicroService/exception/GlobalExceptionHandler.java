package com.StarkIndustries.UserMicroService.exception;

import com.StarkIndustries.UserMicroService.keys.Keys;
import org.hibernate.annotations.CurrentTimestamp;
import org.springframework.data.auditing.CurrentDateTimeProvider;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        Map<String,Object> body = new LinkedHashMap<>();

        body.put(Keys.STATUS,status);

        body.put(Keys.TIME_STAMP, LocalDate.now());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error->
                    error.getDefaultMessage())
                .toList();

        body.put(Keys.ERRORS,errors);

        return new ResponseEntity<>(body,status);
    }
}

package com.foodhub.foodservice.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ErroeDetails> errorHandling(Exception exception){
//        ErroeDetails details=new ErroeDetails(new Date(),
//                exception.getMessage(),"please check once..........",null);
//    return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
//    }
    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ErroeDetails> errorHandlingItemNotFoundException(ItemNotFoundException exception){
        ErroeDetails error=new ErroeDetails(new Date(),
                 "Order Not Sucess","Item Not Avilable ,Please try another.........."
                ,"Try Any Other Recipe");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(RuntimeException.class)
    public final ResponseEntity<Map<String, List<String>>> handleRuntimeExceptions(RuntimeException ex) {
        List<String> errors = Collections.singletonList(ex.getMessage());
        return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    private Map<String, List<String>> getErrorsMap(List<String> errors) {
        Map<String, List<String>> errorResponse = new HashMap<>();
        errorResponse.put("errors", errors);
        return errorResponse;
    }
}

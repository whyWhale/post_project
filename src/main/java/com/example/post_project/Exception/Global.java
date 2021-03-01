package com.example.post_project.Exception;

import com.example.post_project.HttpStatusResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class Global {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> ValidationError(MethodArgumentNotValidException an) {
        return new ResponseEntity<>(of(HttpStatus.NOT_ACCEPTABLE,an.getBindingResult().getFieldError()), HttpStatus.NOT_ACCEPTABLE);
    }

    public static ErrorResponse of(HttpStatus httpStatus, FieldError fieldError)
    {
        if(fieldError!=null)
            return ErrorResponse.of(HttpStatus.NOT_ACCEPTABLE, fieldError.getDefaultMessage());
        else
        {
            return ErrorResponse.of(HttpStatus.NOT_ACCEPTABLE,"정확히 기재해 주세요.");
        }

    }
}

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

import javax.validation.ConstraintViolationException;

@Slf4j
@ControllerAdvice
public class Global {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> ValidationError(MethodArgumentNotValidException an) {
        return new ResponseEntity<>(of(HttpStatus.NOT_ACCEPTABLE,an.getBindingResult().getFieldError()), HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> ConstraintViolationExceptionError(ConstraintViolationException exep)
    {

       ErrorResponse errorResponse= ErrorResponse.of(HttpStatus.NOT_ACCEPTABLE,"올바른 형식의 이메일 주소가 아닙니다.");
        return new ResponseEntity< ErrorResponse>(errorResponse,HttpStatus.NOT_ACCEPTABLE);
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

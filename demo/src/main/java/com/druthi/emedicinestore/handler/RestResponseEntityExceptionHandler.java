package com.druthi.emedicinestore.handler;

import com.druthi.emedicinestore.handlerEntity.ErrorMessage;
import com.druthi.emedicinestore.exception.EntityCannotBeDeletedException;
import com.druthi.emedicinestore.exception.EntityNotCreatedException;
import com.druthi.emedicinestore.exception.EntityNotFoundException;
import com.druthi.emedicinestore.exception.EntityNotUpdatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotCreatedException.class)
    public ResponseEntity<ErrorMessage> entityNotCreatedException(EntityNotCreatedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorMessage> entityNotFoundException(EntityNotFoundException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errorMessage);
    }

    @ExceptionHandler(EntityNotUpdatedException.class)
    public ResponseEntity<ErrorMessage> entityNotUpdatedException(EntityNotUpdatedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorMessage);

    }

    @ExceptionHandler(EntityCannotBeDeletedException.class)
    public ResponseEntity<ErrorMessage> entityCannotBeDeletedException(EntityCannotBeDeletedException exception, WebRequest webRequest){

        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.METHOD_NOT_ALLOWED, exception.getMessage());
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(errorMessage);

    }
}

//    A controller advice allows us to intercept and modify the return values of controller methods, in our case to handle exceptions.

package com.ericpinto.publicationsapp.controller.exceptions;

import com.ericpinto.publicationsapp.domain.service.exceptions.ObjectNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ControllerExceptionHandler {

//    @ExceptionHandler(ObjectNotFoundException.class)
//    public ResponseEntity<StandartError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
//        HttpStatus status = HttpStatus.NOT_FOUND;
//        StandartError err = new StandartError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(),request.getRequestURI());
//        return ResponseEntity.status(status).body(err);
//    }

    @ResponseStatus(HttpStatus.NOT_FOUND) // 404
    @ExceptionHandler(ObjectNotFoundException.class)
    public void handleNotFound(ObjectNotFoundException ex) {
        log.error("Requested user not found");
    }
}

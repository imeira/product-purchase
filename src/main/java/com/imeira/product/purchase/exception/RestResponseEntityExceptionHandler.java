package com.imeira.product.purchase.exception;

import com.imeira.product.purchase.service.exception.InvalidTransactionException;
import com.imeira.product.purchase.service.exception.ObjectAlreadyExistException;
import com.imeira.product.purchase.service.exception.ObjectNotEnabledException;
import com.imeira.product.purchase.service.exception.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class RestResponseEntityExceptionHandler  extends ResponseEntityExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(RestResponseEntityExceptionHandler.class);

    //error 404
    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {
        LOG.error(e.getMessage());
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(System.currentTimeMillis(), status.value(), "Não encontrado", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    //error 409
    @ResponseStatus(CONFLICT)
    @ExceptionHandler({ ObjectAlreadyExistException.class })
    public ResponseEntity<Object> handleObjectAlreadyExist(final
                                                           RuntimeException e, HttpServletRequest request) {
        LOG.error(e.getMessage());
        HttpStatus status = HttpStatus.CONFLICT;
        StandardError err = new StandardError(System.currentTimeMillis(),
                status.value(), "AlreadyExist", e.getMessage(),
                request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    //error 401
    @ResponseStatus(UNAUTHORIZED)
    @ExceptionHandler(value = {ObjectNotEnabledException.class})
    public ResponseEntity<Object> handleObjectNotEnabled(final
                                                         RuntimeException e, HttpServletRequest request) {
        LOG.error(e.getMessage());
        HttpStatus status = HttpStatus.UNAUTHORIZED;
        StandardError err = new StandardError(System.currentTimeMillis(),
                status.value(), "NotEnable", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    //error 400
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(value = {InvalidTransactionException.class})
    public ResponseEntity<Object> handleInvalidTransaction(final
                                                         RuntimeException e, HttpServletRequest request) {
        LOG.error(e.getMessage());
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(System.currentTimeMillis(),
                status.value(), "BadRequest", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

}

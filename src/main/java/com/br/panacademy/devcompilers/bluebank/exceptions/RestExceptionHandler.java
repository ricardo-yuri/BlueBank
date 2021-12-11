package com.br.panacademy.devcompilers.bluebank.exceptions;

import com.amazonaws.services.sns.model.AmazonSNSException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import javax.validation.UnexpectedTypeException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    protected ResponseEntity<ExceptionDetails> handleBadRequest(AccountNotFoundException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Bad Request Exception. " + exception.getLocalizedMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OperationIllegalException.class)
    protected ResponseEntity<ExceptionDetails> handleOperationIllegalException(OperationIllegalException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Bad Request Exception. " + exception.getLocalizedMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ClientNotFoundException.class)
    protected ResponseEntity<ExceptionDetails> handleClientNotFoundException(ClientNotFoundException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Bad Request Exception. " + exception.getLocalizedMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ExceptionDetails> handleConstraintViolationException(ConstraintViolationException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Bad Request Exception. " + exception.getLocalizedMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ExceptionDetails> handleIllegalArgumentException(IllegalArgumentException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Bad Request Exception. " + exception.getLocalizedMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(AmazonSNSException.class)
    protected ResponseEntity<ExceptionDetails> handleAmazonSNSException(AmazonSNSException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Bad Request Exception. " + exception.getLocalizedMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(NoSuchElementException.class)
    protected ResponseEntity<ExceptionDetails> handleNoSuchElementException(NoSuchElementException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Bad Request Exception. " + exception.getLocalizedMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    protected ResponseEntity<ExceptionDetails> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Bad Request Exception. " + exception.getLocalizedMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    protected ResponseEntity<ExceptionDetails> handleUnexpectedTypeException(UnexpectedTypeException exception) {
        return new ResponseEntity<>(
                ExceptionDetails.builder()
                        .title("Bad Request Exception. " + exception.getLocalizedMessage())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details(exception.getMessage())
                        .developerMessage(exception.getClass().getName())
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest webRequest) {
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream().map(field -> field.getField()).collect(Collectors.joining(", "));
        String fieldMessage = fieldErrors.stream().map(field -> field.getDefaultMessage()).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .title("Bad Request Exception. Invalid Field(s).")
                        .status(HttpStatus.BAD_REQUEST.value())
                        .timestamp(LocalDateTime.now())
                        .details("Check the fields.")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldMessage)
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object>
        handleExceptionInternal(Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .title("Bad Request Exception. " + ex.getLocalizedMessage())
                .status(status.value())
                .timestamp(LocalDateTime.now())
                .details(ex.getCause().getMessage())
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity(exceptionDetails, headers, status);
    }
}

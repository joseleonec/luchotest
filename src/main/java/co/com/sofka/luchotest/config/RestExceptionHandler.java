package co.com.sofka.luchotest.config;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import co.com.sofka.luchotest.dto.ApiErrorResponse;
import co.com.sofka.luchotest.exceptions.ResourceAlreadyExistsException;
import co.com.sofka.luchotest.exceptions.SaldoInsuficienteException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
            WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String fieldName = error.getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });

        String detail = errors.toString();

        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(CustomErrorMessages.INVALID_FIELD)
                .detail(detail)
                .statusCode(HttpStatus.BAD_REQUEST)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleConstraintViolationException(ConstraintViolationException ex,
            WebRequest request) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(CustomErrorMessages.INVALID_FIELD)
                .detail(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex,
            WebRequest request) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(CustomErrorMessages.ENTITY_NOT_FOUND)
                .detail(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex,
            WebRequest request) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(CustomErrorMessages.INVALID_FIELD)
                .detail(ex.getMessage())
                .statusCode(HttpStatus.BAD_REQUEST)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidDataAccessApiUsageException.class)
    public ResponseEntity<ApiErrorResponse> handleInvalidDataAccessApiUsageException(
            InvalidDataAccessApiUsageException ex, WebRequest request) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(CustomErrorMessages.INVALID_DATA_ACCESS_API_USAGE)
                .detail(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiErrorResponse> handleDataIntegrityViolationException(
            DataIntegrityViolationException ex, WebRequest request) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(CustomErrorMessages.DATA_INTEGRITY_VIOLATION)
                .detail(ex.getMostSpecificCause().getMessage())
                .statusCode(HttpStatus.CONFLICT)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ApiErrorResponse> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException ex, WebRequest request) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(CustomErrorMessages.RESOURCE_ALREADY_EXISTS)
                .detail(ex.getMessage())
                .statusCode(HttpStatus.CONFLICT)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErrorResponse> handleIllegalStateException(IllegalStateException ex,
            WebRequest request) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(CustomErrorMessages.ILLEGAL_STATE)
                .detail(ex.getMessage())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorResponse> handleNoSuchElementException(NoSuchElementException ex,
            WebRequest request) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(CustomErrorMessages.ENTITY_NOT_FOUND)
                .detail(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(SaldoInsuficienteException.class)
    public ResponseEntity<ApiErrorResponse> handleNoSuchElementException(SaldoInsuficienteException ex,
            WebRequest request) {
        ApiErrorResponse errorResponse = ApiErrorResponse.builder()
                .message(CustomErrorMessages.ILLEGAL_STATE)
                .detail(ex.getMessage())
                .statusCode(HttpStatus.NOT_FOUND)
                .path(request.getDescription(false).replace("uri=", ""))
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}

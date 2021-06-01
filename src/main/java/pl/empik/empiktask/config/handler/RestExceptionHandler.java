package pl.empik.empiktask.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.empik.empiktask.exception.ErrorResponse;
import pl.empik.empiktask.exception.UnrecognizedExternalApiException;
import pl.empik.empiktask.exception.ResourceNotFoundException;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<?> handleResourceNotFoundException(final ResourceNotFoundException ex) {
        var errorResponse = ErrorResponse.create(HttpStatus.NOT_FOUND, "Resource not found", ex.getClass().getSimpleName(), ex.getMessage());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(UnrecognizedExternalApiException.class)
    private ResponseEntity<?> handleExternalApiException(final UnrecognizedExternalApiException ex) {
        var errorResponse = ErrorResponse.create(HttpStatus.INTERNAL_SERVER_ERROR, "Error with external api", ex.getClass().getSimpleName(), ex.getMessage());
        return buildResponseEntity(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity<?> handleException(final Exception ex) {
        var errorResponse = ErrorResponse.create(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error", ex.getClass().getSimpleName(), ex.getMessage());
        return buildResponseEntity(errorResponse);
    }

    private ResponseEntity<?> buildResponseEntity(final ErrorResponse errorResponse) {
        return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
    }

}
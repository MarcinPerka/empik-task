package pl.empik.empiktask.exception;

import org.springframework.http.HttpStatus;

public class UnrecognizedExternalApiException extends RuntimeException {
    public UnrecognizedExternalApiException(final HttpStatus status, final String text) {
        super(String.format("Unrecognized error occurred: status %s, message: %s", status, text));
    }
}
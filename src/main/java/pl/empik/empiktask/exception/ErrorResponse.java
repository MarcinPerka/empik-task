package pl.empik.empiktask.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class ErrorResponse {

    private final HttpStatus status;

    private final String message;

    private final String code;

    private final LocalDateTime timestamp = LocalDateTime.now();

    private final String details;

    public static ErrorResponse create(final HttpStatus httpStatus, final String message, final String code, final String details) {
        return new ErrorResponse(httpStatus, message, code, details);
    }
}
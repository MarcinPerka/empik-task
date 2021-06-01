package pl.empik.empiktask.config.handler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import pl.empik.empiktask.exception.ResourceNotFoundException;
import pl.empik.empiktask.exception.UnrecognizedExternalApiException;

import java.io.IOException;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RestTemplateResponseErrorHandlerTests {

    @InjectMocks
    private RestTemplateResponseErrorHandler errorHandler;

    @Mock
    private ClientHttpResponse clientHttpResponse;

    @Test
    public void shouldHasError_whenApiResponseIsClientError() throws IOException {
        when(clientHttpResponse.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);

        Assertions.assertTrue(errorHandler.hasError(clientHttpResponse));
    }

    @Test
    public void shouldHasError_whenApiResponseIsServerError() throws IOException {
        when(clientHttpResponse.getStatusCode()).thenReturn(HttpStatus.INTERNAL_SERVER_ERROR);

        Assertions.assertTrue(errorHandler.hasError(clientHttpResponse));
    }

    @Test
    public void shouldThrowResourceNotFoundException_whenApiResponseCodeIsNotFound() throws IOException {
        when(clientHttpResponse.getStatusCode()).thenReturn(HttpStatus.NOT_FOUND);

        Assertions.assertThrows(ResourceNotFoundException.class, () -> errorHandler.handleError(clientHttpResponse));
    }

    @Test
    public void shouldThrowUnrecognizedExternalApiException_whenApiResponseCodeIsOtherThanOkAndNotFound() throws IOException {
        when(clientHttpResponse.getStatusCode()).thenReturn(HttpStatus.I_AM_A_TEAPOT);

        Assertions.assertThrows(UnrecognizedExternalApiException.class, () -> errorHandler.handleError(clientHttpResponse));
    }
}

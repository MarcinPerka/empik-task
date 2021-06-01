package pl.empik.empiktask.config.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;
import pl.empik.empiktask.exception.ResourceNotFoundException;
import pl.empik.empiktask.exception.UnrecognizedExternalApiException;

import java.io.IOException;

import static org.springframework.http.HttpStatus.Series.CLIENT_ERROR;
import static org.springframework.http.HttpStatus.Series.SERVER_ERROR;

@Component
@Slf4j
public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

    @Override
    public boolean hasError(ClientHttpResponse httpResponse) throws IOException {
        return isClientError(httpResponse) || isServerError(httpResponse);
    }

    @Override
    public void handleError(ClientHttpResponse httpResponse) throws IOException {
        if (httpResponse.getStatusCode() == HttpStatus.NOT_FOUND)
            throw new ResourceNotFoundException();
        else
            throw new UnrecognizedExternalApiException(httpResponse.getStatusCode(), httpResponse.getStatusText());
    }

    private boolean isClientError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() == CLIENT_ERROR;
    }

    private boolean isServerError(ClientHttpResponse httpResponse) throws IOException {
        return httpResponse.getStatusCode().series() == SERVER_ERROR;
    }
}
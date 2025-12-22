package com.e_library.library_service.client;

import com.e_library.library_service.exception.BookNotFoundException;
import com.e_library.library_service.exception.ExceptionMessage;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.apache.commons.io.IOUtils;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class RetrieveMessageErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ExceptionMessage message = null;
        try {
            if (response.body() != null) {
                try (InputStream body = response.body().asInputStream()) {
                    String date = "Unknown";
                    if (response.headers().containsKey("date") && !response.headers().get("date").isEmpty()) {
                        date = (String) response.headers().get("date").toArray()[0];
                    }

                    HttpStatus httpStatus = HttpStatus.resolve(response.status());
                    String reason = httpStatus != null ? httpStatus.getReasonPhrase() : "Unknown";

                    message = new ExceptionMessage(date,
                            response.status(),
                            reason,
                            IOUtils.toString(body, StandardCharsets.UTF_8),
                            response.request().url());
                }
            }
        } catch (IOException exception) {
            return new Exception(exception.getMessage());
        }
        if (response.status() == 404) {
            throw new BookNotFoundException(message);
        }
        return errorDecoder.decode(methodKey, response);
    }
}

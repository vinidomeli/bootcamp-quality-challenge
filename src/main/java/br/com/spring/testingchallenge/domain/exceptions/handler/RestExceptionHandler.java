package br.com.spring.testingchallenge.domain.exceptions.handler;

import br.com.spring.testingchallenge.domain.exceptions.DistrictNotFoundException;
import br.com.spring.testingchallenge.domain.exceptions.model.DefaultErrorModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(DistrictNotFoundException.class)
    public ResponseEntity<DefaultErrorModel> districtNotFoundHandler(final DistrictNotFoundException ex) {

        final HttpStatus status = ex.getHttpStatus();

        final Integer statusCode = ex.getHttpStatus().value();

        final String message = ex.getMessage();

        final DefaultErrorModel response = DefaultErrorModel.builder()
                .message(message)
                .status(statusCode)
                .build();

        this.logger.info(message);

        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String message = ex.getAllErrors().get(0).getDefaultMessage();

        final DefaultErrorModel response = DefaultErrorModel.builder()
                .status(status.value())
                .message(message)
                .build();

        this.logger.warn("Invalid argument: " + message);

        return new ResponseEntity<>(response, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(final HttpMessageNotReadableException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        final String message = ex.getCause().getCause().getMessage();

        final DefaultErrorModel response = DefaultErrorModel.builder()
                .status(status.value())
                .message(message)
                .build();

        return new ResponseEntity<>(response, status);
    }
}

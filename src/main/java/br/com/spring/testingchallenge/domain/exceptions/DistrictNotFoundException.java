package br.com.spring.testingchallenge.domain.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class DistrictNotFoundException extends RuntimeException{

    private HttpStatus httpStatus = HttpStatus.NOT_FOUND;

    public DistrictNotFoundException() {
        super("District not found.");
    }
}

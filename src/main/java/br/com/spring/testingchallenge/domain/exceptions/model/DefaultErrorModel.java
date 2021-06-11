package br.com.spring.testingchallenge.domain.exceptions.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class DefaultErrorModel {

    private final Integer status;

    private final String message;
}

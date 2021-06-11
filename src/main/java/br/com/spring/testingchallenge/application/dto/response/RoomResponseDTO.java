package br.com.spring.testingchallenge.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponseDTO {

    private String name;

    private Double width;

    private Double height;

    private Double area;
}
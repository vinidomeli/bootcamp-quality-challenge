package br.com.spring.testingchallenge.application.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class HouseResponseDTO {
    private String name;

    private Double value;

    private Double totalArea;

    private String district;

    private List<RoomResponseDTO> rooms;

    private RoomResponseDTO largestRoom;
}

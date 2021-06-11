package br.com.spring.testingchallenge.application.usecase;

import br.com.spring.testingchallenge.application.dto.request.RoomRequestDTO;
import br.com.spring.testingchallenge.application.dto.response.RoomResponseDTO;

import java.util.List;

public interface House {

    public Double totalAreaFrom(final List<RoomRequestDTO> rooms);

    public Double totalPriceFrom(Double totalSize, String district);

    public RoomResponseDTO largestRoomFrom(final List<RoomRequestDTO> rooms);

    public List<RoomResponseDTO> totalAreaByRoomFrom(final List<RoomRequestDTO> rooms);

    public Double calculateArea(final Double width, final Double height);

}

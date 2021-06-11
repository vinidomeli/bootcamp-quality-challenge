package br.com.spring.testingchallenge.application.service;

import br.com.spring.testingchallenge.application.dto.request.RoomRequestDTO;
import br.com.spring.testingchallenge.application.dto.response.RoomResponseDTO;
import br.com.spring.testingchallenge.application.usecase.House;
import br.com.spring.testingchallenge.domain.enumeration.DistrictProperties;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class HouseService implements House {

    @Override
    public Double totalAreaFrom(final List<RoomRequestDTO> rooms) {
        return rooms.stream()
                .map(roomRequestDTO -> calculateArea(roomRequestDTO.getWidth(), roomRequestDTO.getHeight()))
                .reduce(Double::sum)
                .get();
    }

    @Override
    public Double totalPriceFrom(final Double totalSize, final String district) {

        final DistrictProperties districtProperties = DistrictProperties.getBy(district);
        final Double pricePerSqMt = districtProperties.getPricePerSqMeter();

        return totalSize * pricePerSqMt;
    }

    @Override
    public RoomResponseDTO largestRoomFrom(final List<RoomRequestDTO> rooms) {
        final List<RoomResponseDTO> responseRooms = totalAreaByRoomFrom(rooms);

        return responseRooms.stream()
                .max(Comparator.comparing(RoomResponseDTO::getArea))
                .get();
    }

    @Override
    public List<RoomResponseDTO> totalAreaByRoomFrom(final List<RoomRequestDTO> rooms) {
        final List<RoomResponseDTO> roomsResponse = new ArrayList<>();

        rooms.forEach(room -> {
            final String roomName = room.getName();
            final Double height = room.getHeight();
            final Double width = room.getWidth();
            final Double totalArea = calculateArea(room.getWidth(), room.getHeight());

            roomsResponse.add(new RoomResponseDTO(roomName, width, height, totalArea));
        });

        return roomsResponse;
    }

    @Override
    public Double calculateArea(final Double width, final Double height) {
        return width * height;
    }
}

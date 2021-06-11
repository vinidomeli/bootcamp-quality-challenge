package br.com.spring.testingchallenge.application.service;

import br.com.spring.testingchallenge.application.dto.request.RoomRequestDTO;
import br.com.spring.testingchallenge.application.dto.response.RoomResponseDTO;
import br.com.spring.testingchallenge.application.usecase.House;
import br.com.spring.testingchallenge.domain.enumeration.DistrictProperties;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HouseServiceTest {

    private final House service = new HouseService();

    private static Stream<Arguments> areaDataProvider() {
        return Stream.of(
                Arguments.of(8d, 8d),
                Arguments.of(5.6, 8.7),
                Arguments.of(7.2, 5.5)
        );
    }

    private static Stream<Arguments> districtsDataProvider() {
        return Stream.of(
                Arguments.of(127d, DistrictProperties.ALPHAVILLE),
                Arguments.of(234.45, DistrictProperties.ALTO_DA_BOA_VISTA),
                Arguments.of(85.17, DistrictProperties.ITAIM_BIBI),
                Arguments.of(421.15, DistrictProperties.JARDIM_MARAJOARA)
        );
    }

    private static Stream<Arguments> roomsDataProvider() {
        final List<List<RoomRequestDTO>> roomsList = new ArrayList<>();

        for (int j = 0; j < 3; j++) {
            final List<RoomRequestDTO> rooms = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                rooms.add(new RoomRequestDTO("Room " + i, i + 0.5, i + 0.5));
            }
            roomsList.add(rooms);
        }

        return Stream.of(
                Arguments.of(roomsList.get(0)),
                Arguments.of(roomsList.get(1)),
                Arguments.of(roomsList.get(2))
        );
    }

    @ParameterizedTest
    @MethodSource("roomsDataProvider")
    void testTotalAreaFrom(final List<RoomRequestDTO> rooms) {
        final Double room1Area = rooms.get(0).getHeight() * rooms.get(0).getWidth();
        final Double room2Area = rooms.get(1).getHeight() * rooms.get(1).getWidth();

        final Double expectedTotalArea = room1Area + room2Area;

        assertEquals(expectedTotalArea, this.service.totalAreaFrom(rooms));
    }

    @ParameterizedTest
    @MethodSource("districtsDataProvider")
    void totalPriceFrom(final Double area, final DistrictProperties district) {
        final Double expectedPrice = area * district.getPricePerSqMeter();

        assertEquals(expectedPrice, this.service.totalPriceFrom(area, district.getDistrict()));
    }

    @ParameterizedTest
    @MethodSource("roomsDataProvider")
    void largestRoomFrom(final List<RoomRequestDTO> rooms) {
        final List<RoomResponseDTO> roomsResponse = new ArrayList<>();

        final String name = rooms.get(1).getName();
        final Double width = rooms.get(1).getWidth();
        final Double height = rooms.get(1).getHeight();
        final Double area = width * height;

        final RoomResponseDTO expectedLargestRoom = new RoomResponseDTO(name, width, height, area);

        assertEquals(expectedLargestRoom, this.service.largestRoomFrom(rooms));
    }

    @ParameterizedTest
    @MethodSource("roomsDataProvider")
    void totalAreaByRoomFrom(final List<RoomRequestDTO> rooms) {
        final Double room1ExpectedArea = rooms.get(0).getHeight() * rooms.get(0).getWidth();
        final Double room2ExpectedArea = rooms.get(1).getHeight() * rooms.get(1).getWidth();

        final List<RoomResponseDTO> roomsResponse = this.service.totalAreaByRoomFrom(rooms);

        final Double room1CalculatedArea = roomsResponse.get(0).getArea();
        final Double room2CalculatedArea = roomsResponse.get(1).getArea();

        assertEquals(room1ExpectedArea, room1CalculatedArea);
        assertEquals(room2ExpectedArea, room2CalculatedArea);
    }

    @ParameterizedTest
    @MethodSource("areaDataProvider")
    void calculateArea(final Double width, final Double height) {
        final Double expectedArea = width * height;

        assertEquals(expectedArea, this.service.calculateArea(width, height));
    }
}
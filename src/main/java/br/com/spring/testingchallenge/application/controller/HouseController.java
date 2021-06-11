package br.com.spring.testingchallenge.application.controller;

import br.com.spring.testingchallenge.application.dto.request.HouseRequestDTO;
import br.com.spring.testingchallenge.application.dto.request.RoomRequestDTO;
import br.com.spring.testingchallenge.application.dto.response.HouseResponseDTO;
import br.com.spring.testingchallenge.application.dto.response.RoomResponseDTO;
import br.com.spring.testingchallenge.application.usecase.House;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/house")
public class HouseController {

    House houseService;

    public HouseController(final House houseService) {
        this.houseService = houseService;
    }

    @PostMapping("/area")
    public ResponseEntity<HouseResponseDTO> totalAreaOf(@Valid @RequestBody final HouseRequestDTO houseRequestDTO) {

        final List<RoomRequestDTO> rooms = houseRequestDTO.getRooms();

        final HouseResponseDTO response = HouseResponseDTO.builder()
                .name(houseRequestDTO.getName())
                .district(houseRequestDTO.getDistrict())
                .totalArea(this.houseService.totalAreaFrom(rooms))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/price")
    public ResponseEntity<HouseResponseDTO> totalPriceOf(@Valid @RequestBody final HouseRequestDTO houseRequestDTO) {

        final List<RoomRequestDTO> rooms = houseRequestDTO.getRooms();

        final Double totalSize = this.houseService.totalAreaFrom(rooms);

        final HouseResponseDTO response = HouseResponseDTO.builder()
                .name(houseRequestDTO.getName())
                .district(houseRequestDTO.getDistrict())
                .totalArea(totalSize)
                .value(this.houseService.totalPriceFrom(totalSize, houseRequestDTO.getDistrict()))
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/rooms/largest")
    public ResponseEntity<HouseResponseDTO> largestRoomOf(@Valid @RequestBody final HouseRequestDTO houseRequestDTO) {

        final RoomResponseDTO largestRoom = this.houseService.largestRoomFrom(houseRequestDTO.getRooms());

        final HouseResponseDTO response = HouseResponseDTO.builder()
                .name(houseRequestDTO.getName())
                .district(houseRequestDTO.getDistrict())
                .largestRoom(largestRoom)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/rooms/area")
    public ResponseEntity<HouseResponseDTO> areaByRoomFrom(@Valid @RequestBody final HouseRequestDTO houseRequestDTO) {

        final List<RoomResponseDTO> areaByRoom = this.houseService.totalAreaByRoomFrom(houseRequestDTO.getRooms());

        final HouseResponseDTO response = HouseResponseDTO.builder()
                .name(houseRequestDTO.getName())
                .district(houseRequestDTO.getDistrict())
                .rooms(areaByRoom)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

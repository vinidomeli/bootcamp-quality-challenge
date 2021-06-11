package br.com.spring.testingchallenge;

import br.com.spring.testingchallenge.application.dto.request.HouseRequestDTO;
import br.com.spring.testingchallenge.application.dto.request.RoomRequestDTO;
import io.restassured.http.ContentType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestingChallengeIntegrationTests {

    @LocalServerPort
    private int port;

    private static Stream<Arguments> validHouseRequestDTODataProvider() {
        final List<RoomRequestDTO> rooms = new ArrayList<>();
        final RoomRequestDTO room1 = new RoomRequestDTO("Main room", 15d, 13d);
        final RoomRequestDTO room2 = new RoomRequestDTO("Bedroom", 18d, 22d);

        rooms.add(room1);
        rooms.add(room2);

        final String name = "House Testing";
        final String district = "Jardim Marajoara";

        return Stream.of(
                Arguments.of(new HouseRequestDTO(name, district, rooms))
        );
    }

    @ParameterizedTest
    @MethodSource("validHouseRequestDTODataProvider")
    void successCalculateAreaIntegrationTest(final HouseRequestDTO requestDTO) {
        given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/house/area")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("House Testing"))
                .body("totalArea", equalTo(591.00F))
                .body("district", equalTo("Jardim Marajoara"));
    }

    @ParameterizedTest
    @MethodSource("validHouseRequestDTODataProvider")
    void failedToCalculateAreaIntegrationWithAnInvalidNameTest(final HouseRequestDTO requestDTO) {
        requestDTO.setName("Aioj123");
        given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/house/area")
                .then()
                .assertThat()
                .statusCode(400)
                .body("status", equalTo(400))
                .body("message", equalTo("Please provide a valid name."));
    }

    @ParameterizedTest
    @MethodSource("validHouseRequestDTODataProvider")
    void successCalculateTotalPriceIntegrationTest(final HouseRequestDTO requestDTO) {
        given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/house/price")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("House Testing"))
                .body("value", equalTo(1655095.5F))
                .body("totalArea", equalTo(591.0F))
                .body("district", equalTo("Jardim Marajoara"));
    }

    @ParameterizedTest
    @MethodSource("validHouseRequestDTODataProvider")
    void failedToCalculateTotalPriceIntegrationTest(final HouseRequestDTO requestDTO) {
        requestDTO.setDistrict("Jardim Marajara");
        given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/house/price")
                .then()
                .assertThat()
                .statusCode(404)
                .body("status", equalTo(404))
                .body("message", equalTo("District not found."));
    }

    @ParameterizedTest
    @MethodSource("validHouseRequestDTODataProvider")
    void successFindTheLargestRoomIntegrationTest(final HouseRequestDTO requestDTO) {
        given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/house/rooms/largest")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("House Testing"))
                .body("district", equalTo("Jardim Marajoara"))
                .body("largestRoom.name", equalTo("Bedroom"))
                .body("largestRoom.width", equalTo(18F))
                .body("largestRoom.height", equalTo(22F))
                .body("largestRoom.area", equalTo(396F));

    }

    @ParameterizedTest
    @MethodSource("validHouseRequestDTODataProvider")
    void successFindTheAreaOfEachRoomIntegrationTest(final HouseRequestDTO requestDTO) {
        given()
                .port(this.port)
                .contentType(ContentType.JSON)
                .body(requestDTO)
                .when()
                .post("/house/rooms/area")
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("House Testing"))
                .body("district", equalTo("Jardim Marajoara"))
                .body("rooms[0].name", equalTo("Main room"))
                .body("rooms[0].width", equalTo(15F))
                .body("rooms[0].height", equalTo(13F))
                .body("rooms[0].area", equalTo(195F))
                .body("rooms[1].name", equalTo("Bedroom"))
                .body("rooms[1].width", equalTo(18F))
                .body("rooms[1].height", equalTo(22F))
                .body("rooms[1].area", equalTo(396F));
    }

}

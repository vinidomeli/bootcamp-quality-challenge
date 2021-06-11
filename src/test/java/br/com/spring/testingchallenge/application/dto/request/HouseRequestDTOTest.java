package br.com.spring.testingchallenge.application.dto.request;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HouseRequestDTOTest {

    private static ValidatorFactory validatorFactory;
    private static Validator validator;

    @BeforeAll
    public static void createValidator() {
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
    }

    private static Stream<Arguments> validHouseRequestDTODataProvider() {
        final List<RoomRequestDTO> rooms = new ArrayList<>();
        final RoomRequestDTO room1 = new RoomRequestDTO("Main room", 21.5, 12.8);
        final RoomRequestDTO room2 = new RoomRequestDTO("Bedroom", 12.0, 11.0);

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
    public void shouldBeAValidDTOTest(final HouseRequestDTO houseRequestDTO) {

        final Set<ConstraintViolation<HouseRequestDTO>> violations = validator
                .validate(houseRequestDTO);

        assertTrue(violations.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("validHouseRequestDTODataProvider")
    public void invalidNameExceptionTest(final HouseRequestDTO houseRequestDTO) {
        houseRequestDTO.setName("As9d8498");

        final Optional<ConstraintViolation<HouseRequestDTO>> violations = validator
                .validate(houseRequestDTO)
                .stream()
                .findFirst();

        final String expectedMessage = "Please provide a valid name.";

        assertEquals(expectedMessage, violations.get().getMessage());
    }

    @ParameterizedTest
    @MethodSource("validHouseRequestDTODataProvider")
    public void nullNameExceptionTest(final HouseRequestDTO houseRequestDTO) {
        houseRequestDTO.setName(null);

        final Optional<ConstraintViolation<HouseRequestDTO>> violations = validator
                .validate(houseRequestDTO)
                .stream()
                .findFirst();

        final String expectedMessage = "Name cannot be null";

        assertEquals(expectedMessage, violations.get().getMessage());
    }

    @ParameterizedTest
    @MethodSource("validHouseRequestDTODataProvider")
    public void invalidDistrictExceptionTest(final HouseRequestDTO houseRequestDTO) {
        houseRequestDTO.setDistrict("Alto da boa vista");

        final Optional<ConstraintViolation<HouseRequestDTO>> violations = validator
                .validate(houseRequestDTO)
                .stream()
                .findFirst();

        final String expectedMessage = "Invalid district. Each word must start with capital letter, example:(Alto Da Boa Vista)";

        assertEquals(expectedMessage, violations.get().getMessage());
    }

    @ParameterizedTest
    @MethodSource("validHouseRequestDTODataProvider")
    public void nullDistrictExceptionTest(final HouseRequestDTO houseRequestDTO) {
        houseRequestDTO.setDistrict(null);

        final Optional<ConstraintViolation<HouseRequestDTO>> violations = validator
                .validate(houseRequestDTO)
                .stream()
                .findFirst();

        final String expectedMessage = "District cannot be null";

        assertEquals(expectedMessage, violations.get().getMessage());
    }
}
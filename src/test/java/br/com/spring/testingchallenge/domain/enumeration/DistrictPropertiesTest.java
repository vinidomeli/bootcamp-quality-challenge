package br.com.spring.testingchallenge.domain.enumeration;

import br.com.spring.testingchallenge.domain.exceptions.DistrictNotFoundException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DistrictPropertiesTest {

    private static Stream<Arguments> validDistrictsDataProvider() {
        return Stream.of(
                Arguments.of("Jardim Marajoara")
        );
    }

    private static Stream<Arguments> invalidDistrictsDataProvider() {
        return Stream.of(
                Arguments.of("Jardim marajoara"),
                Arguments.of("Alphavixlle"),
                Arguments.of("itaim Bibi"),
                Arguments.of("12312")
        );
    }

    @ParameterizedTest
    @MethodSource("validDistrictsDataProvider")
    void successGetByDistrictTest(final String district) {
        final DistrictProperties expectedDistrict = DistrictProperties.JARDIM_MARAJOARA;

        assertEquals(expectedDistrict, DistrictProperties.getBy(district));
    }

    @ParameterizedTest
    @MethodSource("invalidDistrictsDataProvider")
    void errorWhenGetByDistrictTest(final String district) {
        assertThrows(DistrictNotFoundException.class, () -> {
            DistrictProperties.getBy(district);
        });
    }
}
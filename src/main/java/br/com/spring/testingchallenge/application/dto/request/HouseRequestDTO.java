package br.com.spring.testingchallenge.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
public class HouseRequestDTO {

    @NotNull(message = "Name cannot be null")
    @Pattern(regexp = "^[A-z ]+$",
            message = "Please provide a valid name.")
    @Size(max = 30, message = "House name must have at maximum 30 characters.")
    private String name;

    @NotNull(message = "District cannot be null")
    @Pattern(regexp = "^[A-Z][a-z]+(\\s[A-Z][a-z]+)*$",
            message = "Invalid district. Each word must start with capital letter, example:(Alto Da Boa Vista)")
    @Size(max = 30, message = "District must have at maximum 45 characters.")
    private String district;

    @Valid
    @NotNull(message = "You must provide at least two rooms.")
    @Size(min = 2, message = "You must provide at least two rooms.")
    private List<RoomRequestDTO> rooms;
}

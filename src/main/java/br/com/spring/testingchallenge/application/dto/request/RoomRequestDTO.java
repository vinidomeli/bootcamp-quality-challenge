package br.com.spring.testingchallenge.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequestDTO {
    @NotNull(message = "Room name cannot be null")
    @Pattern(regexp = "^[A-z ]+$",
            message = "Please provide a valid room name.")
    @Size(max = 30, message = "Room name must have at maximum 30 characters.")
    private String name;

    @NotNull(message = "Width cannot be null")
    @Min(value = 1, message = "Your width minimum value must be 1m.")
    @Max(value = 25, message = "Your max width must be 25m.")
    private Double width;

    @NotNull(message = "Height cannot be null")
    @Min(value = 1, message = "Your height minimum value must be 1m.")
    @Max(value = 25, message = "Your max height must be 33m.")
    private Double height;
}

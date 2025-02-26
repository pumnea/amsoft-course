package com.amsoftacademy.touristsheet.adapter.in.rest.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.amsoftacademy.touristsheet.domain.Address}
 */
@Value
public class AddressDto implements Serializable {

    @Size(min = 2, max = 30, message = "Street must be between 2 and 30 characters long")
    @NotBlank(message = "Street is required")
    String street;

    @Size(min = 1, max = 30, message = "City must be between 1 and 30 characters long")
    @NotBlank(message = "City is required")
    String city;

    @Pattern(regexp = "^[A-Za-z0-9]{3,7}$", message = "Invalid postal code")
    @NotBlank(message = "Postal code is required")
    String postalCode;

    @Size(min = 1, max = 30, message = "Country must be between 1 and 30 characters long")
    @NotBlank(message = "Country is required")
    String country;

    @Pattern(regexp = "^[A-Za-z]{2,3}$", message = "Country code must consist of 2 or 3 letters")
    @NotBlank(message = "Country code is required")
    String countryCode;
}
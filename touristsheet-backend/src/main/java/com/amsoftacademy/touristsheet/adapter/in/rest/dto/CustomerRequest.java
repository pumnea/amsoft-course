package com.amsoftacademy.touristsheet.adapter.in.rest.dto;

import lombok.Value;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * DTO for {@link com.amsoftacademy.touristsheet.domain.Customer}
 */
@Value
public class CustomerRequest implements Serializable {

    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters long.")
    @NotBlank(message = "First name is required")
    String firstName;

    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters long.")
    @NotBlank(message = "Last name is required")
    String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    String email;

    @Digits(integer = 11, fraction = 0, message = "Phone number should be maximum 11 digits")
    @NotBlank(message = "Phone is required")
    String phone;

    @Size(max = 100, message = "Passport Number must be less than or equal to 100 characters")
    @NotBlank(message = "Passport Number is required")
    String passportNumber;

    @NotNull(message = "Emergency Contact is required")
    EmergencyContactDto emergencyContact;


    @NotNull(message = "Address is required")
    AddressDto address;
}
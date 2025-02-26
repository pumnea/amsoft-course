package com.amsoftacademy.touristsheet.adapter.in.rest.dto;

import com.amsoftacademy.touristsheet.domain.Address;
import com.amsoftacademy.touristsheet.domain.EmergencyContact;
import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * DTO for {@link com.amsoftacademy.touristsheet.domain.Customer}
 */
@Value
public class CustomerResponse implements Serializable {

    Long id;

    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters long.")
    @NotBlank(message = "First name is required")
    String firstName;

    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters long.")
    @NotBlank(message = "Last name is required")
    String lastName;

    @Email(message = "Invalid email format")
    @NotBlank(message = "Email is required")
    String email;

    @Size(message = "Phone must be less than or equal to 20 characters", max = 20)
    @NotBlank(message = "Phone is required")
    String phone;

    @Size(max = 100, message = "Passport Number must be less than or equal to 100 characters")
    @NotBlank(message = "Passport Number is required")
    String passportNumber;

    Address address;

    @NotNull(message = "Emergency Contact is required")
    EmergencyContact emergencyContact;
}
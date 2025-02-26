package com.amsoftacademy.touristsheet.adapter.in.rest.dto;

import lombok.Value;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * DTO for {@link com.amsoftacademy.touristsheet.domain.TouristSheet}
 */
@Value
public class TouristSheetRequest implements Serializable {

    @NotNull(message = "Customer is required")
    CustomerResponse customer;

    @NotNull(message = "Destination is required")
    @Size(message = "Destination must be less than or equal to 50 characters", max = 50)
    String destination;

    @NotNull(message = "Travel start date is required")
    @FutureOrPresent(message = "Travel start date must be in the future or present")
    LocalDate travelStartDate;

    @NotNull(message = "Travel end date is required")
    @Future(message = "Travel end date must be in the future")
    LocalDate travelEndDate;

    @Size(max = 100, message = "Flight details must be less than or equal to 100 characters")
    @NotBlank(message = "Flight details is required")
    String flightDetails;

    @Size(min = 1, max = 15, message = "Tour plan must contain between 1 and 15 items")
    @NotEmpty(message = "Tour plan must not be empty")
    List<String> tourPlan;
}
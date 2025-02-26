package com.amsoftacademy.touristsheet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

/**
 * This class encapsulates the information related to a Tourist Sheet, including travel dates,
 * flight details, tour plan, associated customer, and timestamps.
 *
 * @author Alex Pumnea
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tourist_sheets", uniqueConstraints = {
        @UniqueConstraint(name = "uc_tourist_sheet_id_customer_id", columnNames = {"id","customer_id"})
})
@NamedEntityGraph(name = "TouristSheet.graph", attributeNodes = {
        @NamedAttributeNode("customer"),
})
public class TouristSheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * The customer associated with the Tourist Sheet.
     */

    @NotNull(message = "Customer is required")
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    /**
     * The customer associated with the Tourist Sheet.
     */

    @NotNull(message = "Destination is required")
    @Size(max = 50, message = "Destination must be less than or equal to 50 characters")
    @Column(name = "destination" )
    private String destination;

    /**
     * The start date of the travel.
     */

    @NotNull(message = "Travel start date is required")
    @FutureOrPresent(message = "Travel start date must be in the future or present")
    @Column(name = "travel_start_date")
    private LocalDate travelStartDate;

    /**
     * The end date of the travel.
     */

    @NotNull(message = "Travel end date is required")
    @Future(message = "Travel end date must be in the future")
    @Column(name = "travel_end_date")
    private LocalDate travelEndDate;

    /**
     * The flight details for the travel.
     */

    @NotBlank(message = "Flight details is required")
    @Size(max = 100, message = "Flight details must be less than or equal to 100 characters")
    @Column(name = "flight_details")
    private String flightDetails;

    /**
     * The tour plan of the travel.
     * It contains a list of items describing the tour plan.
     */

    @NotEmpty(message = "Tour plan must not be empty")
    @Size(min = 1, max = 15, message = "Tour plan must contain between 1 and 15 items")
    @ElementCollection
    @CollectionTable(name = "tour_plan", joinColumns = @JoinColumn(name = "tourist_sheet_id"))
    @Column(name = "item")
    private List<String> tourPlan;


    /**
     * The timestamp indicating the creation time of the tourist sheet.
     */

    @CreationTimestamp
    @Column(name = "created_tm", nullable = false, updatable = false,
            columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Instant createdAt;

    /**
     * The timestamp indicating the last update time of the tourist sheet.
     */

    @UpdateTimestamp
    @Column(name = "updated_tm", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    private Instant updatedAt;
}
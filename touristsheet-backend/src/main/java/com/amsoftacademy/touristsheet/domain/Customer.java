package com.amsoftacademy.touristsheet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * This class represents a Customer entity with associated information such as passport number,
 * emergency contact, address, and tourist sheets.
 * It extends the Person class to inherit common properties first name, last name, email and phone.
 * The customer object can be associated with multiple tourist sheets.
 *
 * @author Alex Pumnea
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers", uniqueConstraints = {
        @UniqueConstraint(name = "uc_customer_first_name",
                columnNames = {"first_name",
                        "last_name",
                        "email",
                        "phone",
                        "passport_number",
                        "emergency_contact_id"})
})
@NamedEntityGraph(name = "Customer_with_address.graph",
        attributeNodes = {
                @NamedAttributeNode("address"),
                @NamedAttributeNode("emergencyContact"),
        })
public class Customer extends Person {

    /**
     * Passport number of the Customer
     */

    @NotBlank(message = "Passport Number is required")
    @Size(max = 100, message = "Passport Number must be less than or equal to 100 characters")
    @Column(name = "passport_number", nullable = false)
    private String passportNumber;

    /**
     * Emergency contact associated with the Customer
     */

    @NotNull(message = "Emergency Contact is required")
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "emergency_contact_id", nullable = false)
    private EmergencyContact emergencyContact;

    /**
     * Address associated with the Customer
     */

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    /**
     * Back reference to the Tourist Sheets associated with the Customer
     */

    @JsonIgnore
    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private Set<TouristSheet> touristSheets = new LinkedHashSet<>();

}
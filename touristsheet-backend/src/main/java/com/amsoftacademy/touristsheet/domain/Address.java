package com.amsoftacademy.touristsheet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * This class represents an Address entity with associated information such as street,
 * city, postal code, country and country code.
 *
 * @author Alex Pumnea
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "addresses", uniqueConstraints = {
        @UniqueConstraint(name = "uc_address_street_city",
                columnNames = {"street", "city", "country"})
})
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Street is required")
    @Size(min = 2, max = 30, message = "Street must be between 2 and 30 characters long")
    @Column(name = "street", nullable = false)
    private String street;

    @NotBlank(message = "City is required")
    @Size(min = 1, max = 30, message = "City must be between 1 and 30 characters long")
    @Column(name = "city", nullable = false)
    private String city;

    @NotBlank(message = "Postal code is required")
    @Pattern(regexp = "^[A-Za-z0-9]{3,7}$", message = "Invalid postal code")
    @Column(name = "postal_code", nullable = false)
    private String postalCode;

    @NotBlank(message = "Country is required")
    @Size(min = 1, max = 30, message = "Country must be between 1 and 30 characters long")
    @Column(name = "country", nullable = false)
    private String country;

    @NotBlank(message = "Country code is required")
    @Pattern(regexp = "^[A-Za-z]{2,3}$", message = "Country code must consist of 2 or 3 letters")
    @Column(name = "country_code", nullable = false)
    private String countryCode;

}
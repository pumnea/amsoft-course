package com.amsoftacademy.touristsheet.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * This class represents an Emergency Contact entity with associated information relationship enum.
 * It extends the Person class to inherit common properties first name, last name, email and phone.
 *
 * @author Alex Pumnea
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "emergency_contacts", uniqueConstraints = {
        @UniqueConstraint(name = "uc_emergency_contact",
                columnNames = {"first_name", "last_name", "email", "phone", "relationship"})
})
public class EmergencyContact extends Person {

    /**
     * Enumeration of available relationships for Emergency Contact in relation to Customer.
     */

    @Enumerated(EnumType.STRING)
    @Column(name = "relationship", nullable = false)
    private Relationship relationship;
}
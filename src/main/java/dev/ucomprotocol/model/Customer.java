package dev.ucomprotocol.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a customer in the commerce system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    /** Unique internal identifier of the customer. */
    private String id;

    /** ID of the customer in the external commerce platform. */
    private String externalId;

    /** Customer's email address. */
    private String email;

    /** Customer's first name. */
    private String firstName;

    /** Customer's last name. */
    private String lastName;

    /** Customer's phone number. */
    private String phoneNumber;
}

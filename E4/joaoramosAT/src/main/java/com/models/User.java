package com.models;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String Name;
    private String Email;
    private String Password;
    private LocalDate Birth;
    private Address Address;
    private EPronouns Pronoun;

    private final boolean NewsletterSignup = false; // always false
    private final boolean SpecialOffersSignup = false; // always false
}

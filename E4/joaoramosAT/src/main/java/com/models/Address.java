package com.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String FirstName;
    private String LastName;
    private String Company;
    private String Address;
    private String Address2;
    private ECountries Country;
    private String State;
    private String City;
    private String Zipcode;
    private String Mobile;
}

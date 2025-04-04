package com.models;


/**
 * @author The Elite Gentleman
 * referencia: https://stackoverflow.com/questions/3978654/best-way-to-create-enum-of-strings
 */
public enum ECountries {
    India("India"),
    United_States("United States"),
    Canada("Canada"),
    Australia("Australia"),
    Israel("Israel"),
    New_Zealand("New Zealand"),
    Singapore("Singapore"),
    ;

    private final String text;
  
    ECountries(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }
}
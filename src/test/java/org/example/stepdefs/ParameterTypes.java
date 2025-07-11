package org.example.stepdefs;

import io.cucumber.java.ParameterType;

public class ParameterTypes {

    @ParameterType("SCIENCE_FICTION|FANTASY|ADVENTURE|FICTION|DRAMA")
    public Genre genre(String genre) {
        System.out.println("[LOG] @ParameterType called with: " + genre);
        return Genre.valueOf(genre);
    }
}

package org.example.stepdefs.parameterTypes;

import io.cucumber.java.ParameterType;
import org.example.stepdefs.Genre;

public class ParameterTypes {

    @ParameterType("SCIENCE_FICTION|FANTASY|ADVENTURE|FICTION|DRAMA")
    public Genre genre(String genre) {
        System.out.println("[LOG] @ParameterType called with: " + genre);
        return Genre.valueOf(genre);
    }
}

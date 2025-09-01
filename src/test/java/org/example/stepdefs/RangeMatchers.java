package org.example.stepdefs;

import com.fasterxml.jackson.databind.JsonNode;

public class RangeMatchers {
    public static boolean rangeMatcher(JsonNode actual, JsonNode expected) {
        String expectedText = expected.asText();
        String prefix = "${json-unit.matches:rangeMatcher}";
        String argsPart = expectedText.startsWith(prefix) ? expectedText.substring(prefix.length()) : "";
        String[] args = argsPart.split(",");
        if (args.length < 2) {
            throw new AssertionError("rangeMatcher expects two arguments (min,max). Got: '" + argsPart + "'");
        }
        double min = Double.parseDouble(args[0].trim());
        double max = Double.parseDouble(args[1].trim());

        double value = actual.isNumber() ? actual.asDouble() : Double.parseDouble(actual.asText());
        boolean result = value >= min && value <= max;
        if (!result) {
            throw new AssertionError(String.format("Value %.2f is NOT within range [%.2f, %.2f]", value, min, max));
        }
        return result;
    }
}
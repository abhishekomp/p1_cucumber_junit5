package org.example.stepdefs;

//import net.javacrumbs.jsonunit.core.matcher.JsonMatcher;
import java.util.regex.Pattern;

public class CustomMatchers {
    /*public static final JsonMatcher ID_MATCHER = (actual, arguments) -> {
        String value = actual.asText();
        Pattern pattern = Pattern.compile("U\\d+-20\\d{2}");
        return pattern.matcher(value).matches() ? null : "ID does not match pattern U\\d+-20\\d{2}";
    };

    public static final JsonMatcher STATUS_MATCHER = (actual, arguments) ->
        "PENDING".equals(actual.asText()) || "ACTIVE".equals(actual.asText())
            ? null : "Status must be PENDING or ACTIVE";

    public static final JsonMatcher TIMESTAMP_MATCHER = (actual, arguments) -> {
        String value = actual.asText();
        // Accepts: 2024-06-01T12:34:56Z or 2024-06-01T12:34:56.123Z
        return value.matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d+)?Z(\\[.*])?")
            ? null : "Not a valid ISO-8601 timestamp: " + value;
    };*/
}
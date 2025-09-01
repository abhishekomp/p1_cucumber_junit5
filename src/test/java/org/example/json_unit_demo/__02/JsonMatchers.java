package org.example.json_unit_demo.__02;

import com.fasterxml.jackson.databind.JsonNode;

public class JsonMatchers {
    public static boolean idMatcher(JsonNode actual, JsonNode expected) {
        return actual.asText().matches("U\\d+-20\\d{2}");
    }
    public static boolean statusMatcher(JsonNode actual, JsonNode expected) {
        return actual.asText().matches("PENDING|ACTIVE");
    }
    public static boolean timestampMatcher(JsonNode actual, JsonNode expected) {
        return actual.asText().matches("\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}(\\.\\d{1,9})?Z(\\[.*])?");
    }
}
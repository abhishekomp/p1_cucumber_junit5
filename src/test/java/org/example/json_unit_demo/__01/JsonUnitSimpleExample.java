package org.example.json_unit_demo.__01;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;

public class JsonUnitSimpleExample {
    public static void main(String[] args) {
        String expected = """
        {
            "name": "Alice",
            "id": "${json-unit.ignore}"
        }
        """;
        String actual = """
        {
            "name": "Alice",
            "id": "ANY_DYNAMIC_VALUE"
        }
        """;

        // Using JsonUnit to compare JSON strings while ignoring the "id" field
        // The "${json-unit.ignore}" placeholder tells JsonUnit to ignore this field during comparison
        // This allows for flexible testing where certain fields may vary dynamically
        // but should not affect the outcome of the test.
        // The assertion will pass as the "id" field is ignored in the comparison.
        // json-unit is a Java library that lets you compare JSON documents in test assertions. It’s smarter than just comparing strings.
        // It allows you to ignore certain fields, check for specific values, and more.
        assertThatJson(actual).isEqualTo(expected); // Passes, id is ignored!
    }
}
package com.discovery.epp.ddx.jefferys.function;

import com.discovery.epp.ddx.jefferys.function.YamlClasspathLoader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.yaml.snakeyaml.Yaml;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Write assertions parsing YAML")
class YamlClasspathLoaderTest {


    @Test
    void testYamlClasspathLoaderForSimpleYaml(){
        var expected = """
                refreshment:
                  breakfast:
                    type: green
                    beverage: tea
                    sugar: {teaspoons: 0}
                  midmorning:
                    type: caramel
                    beverage: latte
                    sugar: {teaspoons: 1}""";
        assertYaml("example1.yaml", expected.trim());
    }

    @Test
    void testYamlClasspathLoaderForYamlWithLists(){
        var expected = """
                refreshment:
                  breakfast:
                    type: black
                    beverage: tea
                    sugar: {teaspoons: 1}
                  evening: {type: red, beverage: wine}
                snacks:
                  evening: {name: pistachios}
                party:
                  guests:
                  - {name: Jack, age: 25, gender: male}
                  - {name: Jane, age: 32, gender: female}
                  - {name: Tom, age: 52, gender: male}
                """;
        assertYaml("example2.yaml", expected.trim());
    }

    private void assertYaml(String yaml, String expected) {
        var actualYaml = new YamlClasspathLoader().apply(yaml);
        var actual = new Yaml().dump(actualYaml).trim();

        assertThat(actual).isEqualTo(expected);
    }

}

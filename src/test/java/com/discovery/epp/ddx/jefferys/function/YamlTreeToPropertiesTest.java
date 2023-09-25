package com.discovery.epp.ddx.jefferys.function;

import com.discovery.epp.ddx.jefferys.function.PropertiesToMap;
import com.discovery.epp.ddx.jefferys.function.YamlClasspathLoader;
import com.discovery.epp.ddx.jefferys.function.YamlTreeToProperties;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
class YamlTreeToPropertiesTest {

    @Test
    void testYamlClasspathLoader(){
        var loader = new YamlClasspathLoader();
        var yaml = loader.apply("example1.yaml");
        var properties = new YamlTreeToProperties().apply(yaml);
        var expected = new PropertiesToMap().apply("example1.properties");
        assertThat(properties).isEqualTo(expected);

    }

}
package com.discovery.epp.ddx.jefferys.function;

import lombok.NonNull;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;
import java.util.function.Function;


public class YamlClasspathLoader implements Function<String, Map<String,?>> {

    @Override
    public Map<String,?> apply(@NonNull String resource) {
        return new Yaml().load(new ClasspathResourceToIInputStream().apply(resource));
    }


}

package com.discovery.epp.ddx.jefferys.function;

import lombok.NonNull;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.Map;
import java.util.function.Function;


public class YamlClasspathLoader implements Function<String, Map<String,?>> {

    @Override
    public Map<String,?> apply(@NonNull String resource) {
        Yaml yaml = new Yaml();
        var inputStream = resourceAsStream(resource);
        return yaml.load(inputStream);
    }

    private InputStream resourceAsStream(String resource) {
        var inputStream = this.getClass().getClassLoader()
                .getResourceAsStream(resource);
        if(inputStream == null){
            throw new IllegalArgumentException("Unable to find "+resource+" as resource on classpath");
        }
        return inputStream;
    }
}

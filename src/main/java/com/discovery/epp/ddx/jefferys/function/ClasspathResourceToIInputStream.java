package com.discovery.epp.ddx.jefferys.function;

import lombok.NonNull;

import java.io.InputStream;
import java.util.function.Function;

public class ClasspathResourceToIInputStream implements Function<String,InputStream> {

    @Override
    public InputStream apply(@NonNull String s) {
        return resourceAsStream(s);
    }

    private InputStream resourceAsStream(String resource) {
        var inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
        if(inputStream == null){
            throw new IllegalArgumentException("Unable to find "+resource+" on classpath");
        }
        return inputStream;
    }

}

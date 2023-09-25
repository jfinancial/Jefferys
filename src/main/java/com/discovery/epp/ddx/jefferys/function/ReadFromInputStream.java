package com.discovery.epp.ddx.jefferys.function;

import lombok.NonNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.function.Function;

public class ReadFromInputStream implements Function<InputStream,List<String>> {

    @Override
    public List<String> apply(@NonNull InputStream inputStream) {
        return new BufferedReader(
                new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines().toList();
    }

}

package com.discovery.epp.ddx.jefferys.function;


import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class PropertiesToMap implements Function<String, Map<String,String>> {

    @Override
    public Map<String, String> apply(@NonNull String s) {
        var result = new HashMap<String,String>();
        var inputStream = new ClasspathResourceToIInputStream().apply(s);
        new ReadFromInputStream().apply(inputStream).stream().forEach(l -> populateMap(result, l));
        return result;
    }

    private void populateMap(Map<String, String> result, String l) {
        var keyValue = l.split("=");
        result.put(keyValue[0].trim(),keyValue[1].trim());
    }

}

package com.discovery.epp.ddx.jefferys.function;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class YamlTreeToProperties implements Function<Map<String,?>,Map<String,?>>{


    @Override
    public Map<String, ?> apply(Map<String, ?> input) {
        return resolve(new HashMap<>(), input, "");
    }

    private Map<String, ?> resolve(Map<String,String> target, Map<String, ?> input, String prefix) {

        input.keySet().stream().forEach(
                key -> {
                    var composedPrefix = composePrefix(prefix, key);
                    var value = input.get(key);
                    if(value instanceof Map){
                        resolve(target, (Map<String, ?>) value, composedPrefix);
                    } else {
                        target.put(composedPrefix, value.toString());
                    }
                }
        );
        return target;
    }

    private String composePrefix(String prefix, String key) {
        return isBlankOrEmpty(prefix) ? key : prefix + "." + key;
    }

    private boolean isBlankOrEmpty(String s){
        return s.isBlank() ||  s.isEmpty();
    }
}

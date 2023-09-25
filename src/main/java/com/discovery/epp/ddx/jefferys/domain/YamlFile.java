package com.discovery.epp.ddx.jefferys.domain;

import org.yaml.snakeyaml.Yaml;
import java.util.Map;

public record YamlFile(String filename, Yaml yaml, Map<String,?> values){}

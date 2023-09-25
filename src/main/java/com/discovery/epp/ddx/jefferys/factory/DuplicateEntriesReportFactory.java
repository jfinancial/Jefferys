package com.discovery.epp.ddx.jefferys.factory;


import com.discovery.epp.ddx.jefferys.domain.DuplicateEntry;
import com.discovery.epp.ddx.jefferys.domain.DuplicateEntriesReport;
import com.discovery.epp.ddx.jefferys.domain.YamlFile;
import lombok.NonNull;


import java.util.*;
import java.util.stream.Collectors;

public class DuplicateEntriesReportFactory implements Factory<List<YamlFile>, DuplicateEntriesReport> {


    @Override
    public DuplicateEntriesReport create(@NonNull List<YamlFile> yamlFiles) {
        var allKeysByFrequency = yamlFiles.stream().flatMap(y -> y.values().keySet().stream())
                .collect(Collectors.groupingBy(e -> e, Collectors.counting()));
        var nonDuplicateKeys = allKeysByFrequency.keySet().stream().filter(k -> allKeysByFrequency.get(k) == 1).collect(Collectors.toSet());
        Map<String, Long> duplicatedKeys = removeDuplicates(allKeysByFrequency, nonDuplicateKeys);
        Map<String, DuplicateEntry> duplicateEntries = new HashMap<>();
        duplicatedKeys.keySet().forEach(k ->
           yamlFiles.stream().filter(y -> y.values().containsKey(k)).forEach(y -> addDuplicateEntry(duplicateEntries, k, y))
        );
        return new DuplicateEntriesReport(duplicateEntries,getNonDuplicates(nonDuplicateKeys,yamlFiles));
    }

    private Map<String,String> getNonDuplicates(Set<String> nonDuplicateKeys, List<YamlFile> yamlFiles) {
        var allEntries = yamlFiles.stream().map(y -> y.values().entrySet()).flatMap(Set::stream).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (r1, r2) -> r1));
        return nonDuplicateKeys.stream().map(k -> Map.entry(k,allEntries.get(k))).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue().toString()));
    }

    private static void addDuplicateEntry(Map<String, DuplicateEntry> duplicates, String k, YamlFile y) {
        var value = y.values().get(k).toString();
        if (duplicates.containsKey(k)) {
            duplicates.get(k).addFileNameAndValue(y.filename(), value);
        } else {
            var duplicate = new DuplicateEntry(k, y.filename(), value);
            duplicates.put(k, duplicate);
        }
    }

    private Map<String, Long> removeDuplicates(Map<String, Long> allKeysByFrequency, Set<String> nonDuplicateKeys) {
        nonDuplicateKeys.forEach(allKeysByFrequency::remove);
        return allKeysByFrequency;
    }

}

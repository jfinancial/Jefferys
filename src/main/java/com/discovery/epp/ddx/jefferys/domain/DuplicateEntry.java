package com.discovery.epp.ddx.jefferys.domain;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Iterables;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@EqualsAndHashCode
@ToString
public class DuplicateEntry {

    public DuplicateEntry(@NonNull String key, @NonNull String filename, @NonNull String value){
        this.key = key;
        this.filenames = new ArrayList<>();
        this.values = new ArrayList<>();
        this.filenames.add(filename);
        this.values.add(value);
    }

    @VisibleForTesting
    public DuplicateEntry(String key, List<String> filenames, List<String> values) {
        this.key = key;
        this.filenames = filenames;
        this.values = values;
    }

    private final String key;
    private final List<String> filenames;

    private final List<String> values;

    public void addFileNameAndValue(@NonNull String filename, @NonNull String value){
        filenames.add(filename);
        values.add(value);
    }

    public int getOccurrences(){
        return filenames.size();
    }

    public String getOverriddenValue(){
        return Iterables.getLast(values);
    }
}

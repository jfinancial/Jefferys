package com.discovery.epp.ddx.jefferys.domain;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Iterables;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;


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
    public DuplicateEntry(String key, List<String> filenames, List<String> values, boolean duplicateValues) {
        this.key = key;
        this.filenames = filenames;
        this.values = values;
        this.duplicateValues = duplicateValues;
    }

    private final String key;
    private final List<String> filenames;

    private final List<String> values;

    private boolean duplicateValues = false;

    public void addFileNameAndValue(@NonNull String filename, @NonNull String value){
        filenames.add(filename);
        if(values.contains(value)){
            this.duplicateValues = true;
        }
        values.add(value);
    }

    public int getOccurrences(){
        return filenames.size();
    }

    public String getOverriddenValue(){
        return Iterables.getLast(values);
    }

}

package com.discovery.epp.ddx.jefferys.domain;

import lombok.NonNull;
import java.util.Map;


public record DuplicateEntriesReport(@NonNull Map<String, DuplicateEntry> duplicates, @NonNull Map<String,String> nonDuplicates){ }

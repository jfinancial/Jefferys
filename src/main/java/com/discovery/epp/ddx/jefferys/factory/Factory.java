package com.discovery.epp.ddx.jefferys.factory;

import lombok.NonNull;

public interface Factory<T,R> {

    R create(@NonNull T source);
}

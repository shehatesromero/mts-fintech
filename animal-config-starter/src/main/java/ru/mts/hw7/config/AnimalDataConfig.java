package ru.mts.hw7.config;

import org.apache.commons.collections4.MapUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import static com.google.common.base.Preconditions.checkArgument;

public class AnimalDataConfig extends ConcurrentHashMap<String, List<String>> {

    private AnimalDataConfig() {
    }

    public static AnimalDataConfig createInstance(Map<String, List<String>> source) {
        checkArgument(MapUtils.isNotEmpty(source), "'source' is null or empty");

        final var result = new AnimalDataConfig();
        source.forEach((k, v) -> {
            if (Objects.isNull(v)) {
                throw new IllegalArgumentException("Error");
            }

            result.put(k, v);
        });

        return result;
    }

}
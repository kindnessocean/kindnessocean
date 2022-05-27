package com.kindnessocean.shared.model.util;

import java.util.Map;
import java.util.Properties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConverterUtil {

    public static Properties convertMapToProperties(Map<String, Object> map) {
        Properties properties = new Properties();

        properties.putAll(map);

        return properties;
    }
}

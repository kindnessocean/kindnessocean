package com.kindnessocean.shared.mq.service;

import java.util.AbstractMap;

public interface KStreamExceptionHandler<K1, V1, K2, V2> {
    AbstractMap.SimpleEntry<K2, V2> handleException(K1 key, V1 value, Exception e);
}

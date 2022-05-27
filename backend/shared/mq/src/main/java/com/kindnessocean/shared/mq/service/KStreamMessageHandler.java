package com.kindnessocean.shared.mq.service;

import java.util.AbstractMap;

public interface KStreamMessageHandler<K1, V1, K2, V2> {
    AbstractMap.SimpleEntry<K2, V2> handleMessage(K1 key, V1 value);
}

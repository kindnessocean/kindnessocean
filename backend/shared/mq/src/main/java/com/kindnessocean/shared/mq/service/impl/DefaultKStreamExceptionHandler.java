package com.kindnessocean.shared.mq.service.impl;

import com.kindnessocean.shared.mq.service.KStreamExceptionHandler;
import java.util.AbstractMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DefaultKStreamExceptionHandler<K1, V1, K2, V2> implements KStreamExceptionHandler<K1, V1, K2, V2> {

    @Override
    public AbstractMap.SimpleEntry<K2, V2> handleException(final Object key, final Object value, final Exception e) {
        log.error("Exception while processing key={} and value={] pair", key, value);
        log.error("Excpetion:", e);

        throw new UnsupportedOperationException("This method doesn't support");
    }
}

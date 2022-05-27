package com.kindnessocean.shared.model.util;

import java.util.UUID;
import org.springframework.stereotype.Component;

@Component
public class UuidUtil {

    public UUID randomUUID() {
        return UUID.randomUUID();
    }
}

package com.malike.gitops.poc.config;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ObservabilityHandler implements ObservationHandler<Observation.Context> {

    @Override
    public void onError(Observation.Context context) {
        log.error(String.format("Error: [%s] : %s", context.getName(), context.getError()));
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}

package com.malike.gitops.poc.service;

import com.malike.gitops.poc.model.Data;
import com.malike.gitops.poc.util.Enums;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class ObservabilityService {

    @Autowired private ObservationRegistry registry;
    List<String> userTypes =
            Collections.unmodifiableList(Arrays.asList("ADMIN", "SUPER_USER", "USER"));

    public void observeCreatedData(Data data, Enums.ObservabilityEvents observabilityEvents) {
        Observation.createNotStarted(observabilityEvents.getMetricName(), registry)
                .lowCardinalityKeyValue("dataId", data.getId())
                .lowCardinalityKeyValue("userType", userTypes.stream().findAny().get())
                .highCardinalityKeyValue("createdBy", data.getCreatedBy())
                .observe(
                        () -> {
                            log.debug("Observing created data");
                        });
    }

    public void observeReadingData(String username) {
        Observation.createNotStarted(Enums.ObservabilityEvents.READ_DATA.getMetricName(), registry)
                .lowCardinalityKeyValue("userType", userTypes.stream().findAny().get())
                .highCardinalityKeyValue("userName", username)
                .observe(
                        () -> {
                            log.debug("Reading data");
                        });
    }
}

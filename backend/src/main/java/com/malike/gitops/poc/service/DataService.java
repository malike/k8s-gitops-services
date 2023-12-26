package com.malike.gitops.poc.service;

import com.malike.gitops.poc.model.Data;
import com.malike.gitops.poc.repository.DataRepository;
import com.malike.gitops.poc.util.Enums;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DataService {

    @Autowired private DataRepository dataRepository;
    @Autowired private ObservabilityService observabilityService;

    public List<Data> getAll(String username) {
        log.info("fetching data at service layer");
        observabilityService.observeReadingData(username);
        return dataRepository.findAll();
    }

    public void createDummy(String user) {
        try {
            log.info(String.format("Creating dummy data for %s", user));
            Data d =
                    Data.builder()
                            .id(RandomStringUtils.randomNumeric(5))
                            .name("Sample Dummy Data")
                            .createdBy(user.toUpperCase())
                            .build();
            dataRepository.save(d);
            observabilityService.observeCreatedData(d, Enums.ObservabilityEvents.CREATE_DUMMY_DATA);
        } catch (Exception e) {
            log.error("error " + e.getMessage());
        }
    }

    public void createData(String user, String name) {
        try {
            log.info(String.format("Creating data for %s", user));
            Data d =
                    Data.builder()
                            .id(RandomStringUtils.randomNumeric(5))
                            .name(name)
                            .createdBy(user)
                            .build();
            dataRepository.save(d);
            observabilityService.observeCreatedData(d, Enums.ObservabilityEvents.CREATE_DATA);
        } catch (Exception e) {
            log.error("error " + e.getMessage());
        }
    }
}

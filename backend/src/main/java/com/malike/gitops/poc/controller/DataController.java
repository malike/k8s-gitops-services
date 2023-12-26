package com.malike.gitops.poc.controller;

import com.malike.gitops.poc.model.Data;
import com.malike.gitops.poc.service.DataService;
import io.micrometer.observation.annotation.Observed;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class DataController {

    @Autowired private DataService dataService;

    @Observed(name = "data.controller", contextualName = "get-all")
    @GetMapping("/data")
    List<Data> getAll() {
        log.info("Fetching all data");
        return dataService.getAll(RandomStringUtils.randomAlphabetic(10));
    }

    @Observed(name = "data.controller", contextualName = "post-dummy-data")
    @PostMapping("/data/dummy")
    void postDummyData() {
        log.info("Posting dummy data");
        dataService.createDummy(RandomStringUtils.randomAlphabetic(10));
    }

    @Observed(name = "data.controller", contextualName = "post-data")
    @PostMapping("/data")
    void postData(String name) {
        log.info("Posting data");
        dataService.createData(RandomStringUtils.randomAlphabetic(10), name);
    }
}

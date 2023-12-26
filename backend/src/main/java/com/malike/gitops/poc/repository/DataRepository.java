package com.malike.gitops.poc.repository;

import com.malike.gitops.poc.model.Data;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface DataRepository extends MongoRepository<Data, String> {}

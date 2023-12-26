package com.malike.gitops.poc.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("attendees")
@lombok.Data
@EqualsAndHashCode
@Builder
public class Data {

    @Id private String id;
    private String name;
    private String createdBy;
}

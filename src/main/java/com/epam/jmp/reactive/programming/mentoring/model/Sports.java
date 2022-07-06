package com.epam.jmp.reactive.programming.mentoring.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
public class Sports {
    private String id;
    private String name;
    private String type;
    private Map<String, Object> attributes;
}

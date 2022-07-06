package com.epam.jmp.reactive.programming.mentoring.model;

import lombok.Data;

import java.util.Map;

@Data
public class Sports {
    private String id;
    private String type;
    private Map<String, Object> attributes;
}

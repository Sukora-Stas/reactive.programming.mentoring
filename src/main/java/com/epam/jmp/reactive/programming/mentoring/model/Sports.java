package com.epam.jmp.reactive.programming.mentoring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Data
@Document
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sports {
    private String id;
    private String name;
    private String type;
    private Map<String, Object> attributes;
}

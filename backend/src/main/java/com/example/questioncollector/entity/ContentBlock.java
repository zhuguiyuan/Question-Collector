package com.example.questioncollector.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContentBlock {
    private ContentType type;

    @Lob
    private String data;

    public enum ContentType {
        TEXT, IMAGE, MARKDOWN
    }
}
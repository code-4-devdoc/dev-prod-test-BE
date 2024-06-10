// Section.java

package com.devdoc.backend.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Section {
    private String id;
    private String content;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

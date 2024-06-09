// Project.java

package com.devdoc.backend.model;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "resume_id")
    private Long resumeId;

    @Column(name = "project_title")
    private String title;

    @Column(name = "project_content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "project_period")
    private String period;

    @Column(name = "is_current")
    private Boolean current;
}

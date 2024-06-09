// ResumeForm.java

package com.devdoc.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResumeForm {
    private Long resumeId;
    private List<Skill> skills;
    private List<Project> projects;
}

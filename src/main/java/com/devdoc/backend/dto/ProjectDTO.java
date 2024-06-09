// ProjectDTO.java

package com.devdoc.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {
    private Long id;
    private Long resumeId;
    private String title;
    private String content;
    private String period;
    private Boolean current;
}

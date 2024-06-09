// ResumeController.java

package com.devdoc.backend.controller;

import com.devdoc.backend.dto.ResumeDTO;
import com.devdoc.backend.model.ResumeForm;
import com.devdoc.backend.service.ResumeService;
import com.devdoc.backend.service.SkillService;
import com.devdoc.backend.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/resumes")
public class ResumeController {

    private final ResumeService resumeService;
    private final SkillService skillService;
    private final ProjectService projectService;

    public ResumeController(ResumeService resumeService, SkillService skillService, ProjectService projectService) {
        this.resumeService = resumeService;
        this.skillService = skillService;
        this.projectService = projectService;
    }

    @GetMapping
    // Resume 조회 (resume_id만)
    public List<ResumeDTO> getAllResumes() {
        return resumeService.getAllResumesByUser();
    }

    @PostMapping
    // Resume 생성 (resume_id만)
    public ResumeDTO createResume() {
        return resumeService.createResume();
    }

    @GetMapping("/{id}")
    // Resume 조회 (Skill 항목, Project 항목)
    public ResumeForm getResumeById(@PathVariable Long id) {
        return resumeService.getResumeById(id);
    }

    @DeleteMapping("/{id}")
    // Resume 삭제
    public void deleteResume(@PathVariable Long id) {
        // resume_id 와 연결된 Resume, Skill, Project 테이블 삭제
        skillService.deleteAllSkillsByResumeId(id);
        projectService.deleteAllProjectsByResumeId(id);
        resumeService.deleteResume(id);
    }
}

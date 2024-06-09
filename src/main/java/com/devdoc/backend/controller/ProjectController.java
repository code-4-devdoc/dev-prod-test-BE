// ProjectController.java

package com.devdoc.backend.controller;

import com.devdoc.backend.dto.ProjectDTO;
import com.devdoc.backend.service.ProjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/resumes/{resume_id}/projects")
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    // Project 전체 조회
    public List<ProjectDTO> getAllProjectsByResumeId(@PathVariable("resume_id") Long resumeId) {
        return projectService.getAllProjectsByResumeId(resumeId);
    }

    @DeleteMapping
    // Project 전체 삭제
    public void deleteAllProjectsByResumeId(@PathVariable("resume_id") Long resumeId) {
        projectService.deleteAllProjectsByResumeId(resumeId);
    }

    @PostMapping
    // Project 생성
    public ProjectDTO createProject(@PathVariable("resume_id") Long resumeId, @RequestBody ProjectDTO projectDTO) {
        return projectService.createProject(resumeId, projectDTO);
    }

    @GetMapping("/{id}")
    // Project 조회
    public ProjectDTO getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PutMapping("/{id}")
    // Project 수정
    public ProjectDTO updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(id, projectDTO);
    }

    @DeleteMapping("/{id}")
    // Project 삭제
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }
}

// ProjectService.java

package com.devdoc.backend.service;

import com.devdoc.backend.dto.ProjectDTO;
import com.devdoc.backend.model.Project;
import com.devdoc.backend.repository.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {

    private final ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<ProjectDTO> getAllProjectsByResumeId(Long resumeId) {
        List<Project> projects = projectRepository.findByResumeId(resumeId);
        return projects.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteAllProjectsByResumeId(Long resumeId) {
        List<Project> projects = projectRepository.findByResumeId(resumeId);
        projectRepository.deleteAll(projects);
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id).orElse(null);
        return convertToDTO(project);
    }

    public ProjectDTO createProject(Long resumeId, ProjectDTO projectDTO) {
        Project project = convertToEntity(projectDTO);
        project.setResumeId(resumeId);
        Project savedProject = projectRepository.save(project);
        return convertToDTO(savedProject);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(id).orElse(null);
        if (existingProject != null) {
            existingProject.setTitle(projectDTO.getTitle());
            existingProject.setContent(projectDTO.getContent());
            existingProject.setPeriod(projectDTO.getPeriod());
            existingProject.setCurrent(projectDTO.getCurrent());
            Project updatedProject = projectRepository.save(existingProject);
            return convertToDTO(updatedProject);
        }
        return null;
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    // 엔티티를 DTO로 변환하는 메서드
    public ProjectDTO convertToDTO(Project project) {
        if (project == null) {
            return null;
        }
        return new ProjectDTO(project.getId(), project.getResumeId(), project.getTitle(), project.getContent(), project.getPeriod(), project.getCurrent());
    }

    // DTO를 엔티티로 변환하는 메서드
    public Project convertToEntity(ProjectDTO projectDTO) {
        if (projectDTO == null) {
            return null;
        }
        return new Project(projectDTO.getId(), projectDTO.getResumeId(), projectDTO.getTitle(), projectDTO.getContent(), projectDTO.getPeriod(), projectDTO.getCurrent());
    }
}

// ResumeService.java

package com.devdoc.backend.service;

import com.devdoc.backend.dto.ResumeDTO;
import com.devdoc.backend.dto.SkillDTO;
import com.devdoc.backend.dto.ProjectDTO;
import com.devdoc.backend.model.Resume;
import com.devdoc.backend.model.ResumeForm;
import com.devdoc.backend.model.Skill;
import com.devdoc.backend.model.Project;
import com.devdoc.backend.repository.ResumeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResumeService {

    private final ResumeRepository resumeRepository;
    private final SkillService skillService;
    private final ProjectService projectService;

    public ResumeService(ResumeRepository resumeRepository, SkillService skillService, ProjectService projectService) {
        this.resumeRepository = resumeRepository;
        this.skillService = skillService;
        this.projectService = projectService;
    }

    public List<ResumeDTO> getAllResumesByUser() {
        List<Resume> resumes = resumeRepository.findAll();
        return resumes.stream()
                .map(resume -> new ResumeDTO(resume.getId()))
                .collect(Collectors.toList());
    }

    public ResumeForm getResumeById(Long id) {
        Resume resume = resumeRepository.findById(id).orElse(null);
        if (resume == null) {
            return null;
        }
        List<SkillDTO> skillDTOs = skillService.getAllSkillsByResumeId(id);
        List<ProjectDTO> projectDTOs = projectService.getAllProjectsByResumeId(id);

        List<Skill> skills = skillDTOs.stream()
                .map(skillService::convertToEntity)
                .collect(Collectors.toList());
        List<Project> projects = projectDTOs.stream()
                .map(projectService::convertToEntity)
                .collect(Collectors.toList());

        return new ResumeForm(resume.getId(), skills, projects);
    }

    public ResumeDTO createResume() {
        Resume resume = new Resume();
        resume = resumeRepository.save(resume);
        return new ResumeDTO(resume.getId());
    }

    public void deleteResume(Long id) {
        resumeRepository.deleteById(id);
    }
}

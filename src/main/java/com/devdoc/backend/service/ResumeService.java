// ResumeService.java

package com.devdoc.backend.service;

import com.devdoc.backend.model.Resume;
import com.devdoc.backend.repository.ResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    @Autowired
    private UserService userService;

    // Resume 조회
    public Resume getResumeById(Long resumeId) {

        if (!userService.isOwner(resumeId)) {
            throw new IllegalStateException("Unauthorized access to view resume");
        }
        
        return resumeRepository.findById(resumeId)
            .orElseThrow(() -> new IllegalStateException("Resume not found"));
    }

    // Resume 생성
    public void createResume(Resume resume) {
        resumeRepository.save(resume);
    }

    // Resume 저장 (= 기존 파일에 덮어쓰기)
    public void saveResume(Resume resume) {
        Resume originalResume = getResumeById(resume.getId());

        if (!userService.isOwner(resume.getId())) {
            throw new IllegalStateException("Unauthorized access to save resume");
        }
    
        originalResume.setTitle(resume.getTitle());
        originalResume.setSkills(resume.getSkills());
        originalResume.setProjects(resume.getProjects());
        resumeRepository.save(originalResume);
    }

    // Resume 삭제
    public void deleteResume(Long resumeId) {
        if (!userService.isOwner(resumeId)) {
            throw new IllegalStateException("Unauthorized access to delete resume");
        }
    
        resumeRepository.deleteById(resumeId);
    }

    // PDF 추출 (미완)
    public ByteArrayInputStream generatePdf(Long resumeId) {
        if (!userService.isOwner(resumeId)) {
            throw new IllegalStateException("Unauthorized access to generate PDF for resume");
        }
    
        // 
        // 
        return new ByteArrayInputStream(new byte[0]);
    }
}

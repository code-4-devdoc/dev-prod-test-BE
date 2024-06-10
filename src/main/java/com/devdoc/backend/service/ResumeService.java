// ResumeService.java

package com.devdoc.backend.service;

import com.devdoc.backend.model.Resume;
import com.devdoc.backend.repository.ResumeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    // 이력서 목록 조회
    public List<Resume> getAllResumes() {
        return resumeRepository.findAll();
    }

    // 이력서 저장
    public Resume saveResume(Resume resume) {
        resume.setCreatedAt(LocalDateTime.now());
        return resumeRepository.save(resume);
    }

    public ByteArrayInputStream generatePdf(Long id) {


        return new ByteArrayInputStream(new byte[0]);
    }}

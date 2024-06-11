// ResumeController.java

package com.devdoc.backend.controller;

import com.devdoc.backend.model.Resume;
import com.devdoc.backend.service.UserService;
import com.devdoc.backend.service.ResumeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@RequestMapping("/api/auth/resumes")
public class ResumeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResumeService resumeService;

    // List<Resume> 조회
    @GetMapping
    public ResponseEntity<List<Resume>> getAllResumes() {
        try {
            List<Resume> resumes = userService.getAllResumesByUser();
            
            return new ResponseEntity<>(resumes, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    
    // Resume 생성
    @PostMapping
    public ResponseEntity<Void> createResume(@RequestBody Resume resume) {
        try {
            resumeService.createResume(resume);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    // Resume 조회
    @GetMapping("/{resumeId}")
    public ResponseEntity<Resume> getResumeById(@PathVariable Long resumeId) {
        try {
            Resume resume = resumeService.getResumeById(resumeId);

            return new ResponseEntity<>(resume, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    
    // Resume 저장 (= 기존 파일에 덮어쓰기)
    @PostMapping("/{resumeId}")
    public ResponseEntity<Void> saveResume(@PathVariable Long resumeId, @RequestBody Resume resume) {
        try {
            resume.setId(resumeId);
            resumeService.saveResume(resume);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    
    // Resume 삭제
    @DeleteMapping("/{resumeId}")
    public ResponseEntity<Void> deleteResume(@PathVariable Long resumeId) {
        try {
            resumeService.deleteResume(resumeId);

            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    

    // PDF 추출 (미완)
    @GetMapping("/{resumeId}/download")
    public ResponseEntity<InputStreamResource> downloadResume(@PathVariable Long userId, @PathVariable Long resumeId) {
        try {
            ByteArrayInputStream bis = resumeService.generatePdf(resumeId);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "inline; filename=resume.pdf");
    
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(new InputStreamResource(bis));

        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
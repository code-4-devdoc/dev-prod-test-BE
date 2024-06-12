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
@RequestMapping("/api/resumes")
public class ResumeController {

    @Autowired
    private UserService userService;

    @Autowired
    private ResumeService resumeService;

    // --------------------------------- 테스트코드 --------------------------------- //

    // HelloBackend
    @GetMapping("/hello")
    public ResponseEntity<String> helloBackend() {
        return ResponseEntity.ok("HelloBackend");
    }

    // List<Resume> 조회
    @GetMapping("/test")
    public ResponseEntity<List<Resume>> getAllResumesTest() {
            List<Resume> resumes = userService.getAllResumesByUser();
            
            return new ResponseEntity<>(resumes, HttpStatus.OK);
    }
    
    // Resume 생성
    @PostMapping("/test")
    public ResponseEntity<Void> createResumeTest(@RequestBody Resume resume) {
            resumeService.createResume(resume);

            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Resume 조회
    @GetMapping("/test/{resumeId}")
    public ResponseEntity<Resume> getResumeByIdTest(@PathVariable Long resumeId) {
            Resume resume = resumeService.getResumeById(resumeId);

            return new ResponseEntity<>(resume, HttpStatus.OK);
    }
    
    // Resume 저장 (= 기존 파일에 덮어쓰기)
    @PostMapping("/test/{resumeId}")
    public ResponseEntity<Void> saveResumeTest(@PathVariable Long resumeId, @RequestBody Resume resume) {
            resume.setId(resumeId);
            resumeService.saveResume(resume);

            return new ResponseEntity<>(HttpStatus.OK);
    }
    
    // Resume 삭제
    @DeleteMapping("/test/{resumeId}")
    public ResponseEntity<Void> deleteResumeTest(@PathVariable Long resumeId) {
            resumeService.deleteResume(resumeId);

            return new ResponseEntity<>(HttpStatus.OK);
    }

    // ---------------------------------- 기존코드 ---------------------------------- //

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
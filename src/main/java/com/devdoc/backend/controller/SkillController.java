// SkillController.java

package com.devdoc.backend.controller;

import com.devdoc.backend.dto.SkillDTO;
import com.devdoc.backend.service.SkillService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/resumes/{resume_id}/skills")
public class SkillController {

    private final SkillService skillService;

    public SkillController(SkillService skillService) {
        this.skillService = skillService;
    }

    @GetMapping
    // Skill 전체 조회
    public List<SkillDTO> getAllSkillsByResumeId(@PathVariable("resume_id") Long resumeId) {
        return skillService.getAllSkillsByResumeId(resumeId);
    }

    @DeleteMapping
    // Skill 전체 삭제
    public void deleteAllSkillsByResumeId(@PathVariable("resume_id") Long resumeId) {
        skillService.deleteAllSkillsByResumeId(resumeId);
    }

    @PostMapping
    // Skill 생성
    public SkillDTO createSkill(@PathVariable("resume_id") Long resumeId, @RequestBody SkillDTO skillDTO) {
        return skillService.createSkill(resumeId, skillDTO);
    }

    @GetMapping("/{id}")
    // Skill 조회
    public SkillDTO getSkillById(@PathVariable Long id) {
        return skillService.getSkillById(id);
    }

    @PutMapping("/{id}")
    // Skill 수정
    public SkillDTO updateSkill(@PathVariable Long id, @RequestBody SkillDTO skillDTO) {
        return skillService.updateSkill(id, skillDTO);
    }

    @DeleteMapping("/{id}")
    // Skill 삭제
    public void deleteSkill(@PathVariable Long id) {
        skillService.deleteSkill(id);
    }
}

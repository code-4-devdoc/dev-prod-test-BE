// SkillService.java

package com.devdoc.backend.service;

import com.devdoc.backend.dto.SkillDTO;
import com.devdoc.backend.model.Skill;
import com.devdoc.backend.repository.SkillRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SkillService {

    private final SkillRepository skillRepository;

    public SkillService(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    public List<SkillDTO> getAllSkillsByResumeId(Long resumeId) {
        List<Skill> skills = skillRepository.findByResumeId(resumeId);
        return skills.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void deleteAllSkillsByResumeId(Long resumeId) {
        List<Skill> skills = skillRepository.findByResumeId(resumeId);
        skillRepository.deleteAll(skills);
    }

    public SkillDTO getSkillById(Long id) {
        Skill skill = skillRepository.findById(id).orElse(null);
        return convertToDTO(skill);
    }

    public SkillDTO createSkill(Long resumeId, SkillDTO skillDTO) {
        Skill skill = convertToEntity(skillDTO);
        skill.setResumeId(resumeId);
        Skill savedSkill = skillRepository.save(skill);
        return convertToDTO(savedSkill);
    }

    public SkillDTO updateSkill(Long id, SkillDTO skillDTO) {
        Skill existingSkill = skillRepository.findById(id).orElse(null);
        if (existingSkill != null) {
            existingSkill.setTitle(skillDTO.getTitle());
            existingSkill.setContent(skillDTO.getContent());
            Skill updatedSkill = skillRepository.save(existingSkill);
            return convertToDTO(updatedSkill);
        }
        return null;
    }

    public void deleteSkill(Long id) {
        skillRepository.deleteById(id);
    }

    // 엔티티를 DTO로 변환하는 메서드
    public SkillDTO convertToDTO(Skill skill) {
        if (skill == null) {
            return null;
        }
        return new SkillDTO(skill.getId(), skill.getResumeId(), skill.getTitle(), skill.getContent());
    }

    // DTO를 엔티티로 변환하는 메서드
    public Skill convertToEntity(SkillDTO skillDTO) {
        if (skillDTO == null) {
            return null;
        }
        return new Skill(skillDTO.getId(), skillDTO.getResumeId(), skillDTO.getTitle(), skillDTO.getContent());
    }
}

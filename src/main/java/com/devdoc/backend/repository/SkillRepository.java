// SkillRepository.java

package com.devdoc.backend.repository;

import com.devdoc.backend.model.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SkillRepository extends JpaRepository<Skill, Long> {
    List<Skill> findByResumeId(Long resumeId);
}

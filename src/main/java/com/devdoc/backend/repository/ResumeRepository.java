// ResumeRepository.java

package com.devdoc.backend.repository;

import com.devdoc.backend.model.Resume;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@SuppressWarnings("null")
public interface ResumeRepository extends JpaRepository<Resume, Long> {
    Optional<Resume> findById(Long id);
}

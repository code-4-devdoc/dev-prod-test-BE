// ProjectRepository.java

package com.devdoc.backend.repository;

import com.devdoc.backend.model.Project;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

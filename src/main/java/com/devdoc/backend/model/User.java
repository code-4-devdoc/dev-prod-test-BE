// UserUser.java

package com.devdoc.backend.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "UserUser")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false, length = 255)
    private String email;
    
    @Column(nullable = false, length = 255)
    private String password;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<Resume> resumes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Resume> getResumes() {
        return resumes;
    }

    public void setResumes(List<Resume> resumes) {
        this.resumes = resumes;
    }
}

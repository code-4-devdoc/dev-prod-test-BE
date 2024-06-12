// UserService.java

package com.devdoc.backend.service;

import com.devdoc.backend.model.User;
import com.devdoc.backend.model.Resume;
import com.devdoc.backend.repository.UserRepository;
import com.devdoc.backend.repository.ResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ResumeRepository resumeRepository;

    // --------------------------------- 테스트코드 --------------------------------- //

    // List<Resume> 조회 - Test (Test User)
    public List<Resume> getAllResumesByUserTest() {
        Long userId = 1L;

        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId))
                .getResumes();
    }

    // ---------------------------------- 기존코드 ---------------------------------- //

    // User 저장
    public void saveUser(User user) {
        userRepository.save(user);
    }

    // 현재 로그인 Username 조회
    public String getCurrentUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            return userDetails.getUsername();
        }
        
        return null;
    }

    // 현재 로그인 UserId 조회
    public Long getCurrentUserId() {
        String username = getCurrentUsername();

        if (username != null) {
            Optional<User> optionalUser = userRepository.findByEmail(username);

            if (optionalUser.isPresent()) {
                return optionalUser.get().getId();
            }
        }

        return null;
    }

    // 현재 로그인 Username =?= Resume 소유자 확인
    public boolean isOwner(Long resumeId) {
        String currentUsername = getCurrentUsername();

        if (currentUsername == null) {                  // 로그인 여부
            return false;
        }

        Optional<Resume> optionalResume = resumeRepository.findById(resumeId);

        if (optionalResume.isEmpty()) {                 // Resume ID 존재 여부
            return false;
        }

        Resume resume = optionalResume.get();
        User ownerUser = resume.getUserId();
        Long ownerUserId = ownerUser.getId();
        Optional<User> optionalUser = userRepository.findById(ownerUserId);

        if (optionalUser.isEmpty()) {                   // User ID 존재 여부
            return false;
        }

        User user = optionalUser.get();
        String ownerUsername = user.getEmail();

        return currentUsername.equals(ownerUsername);
    }    

    // List<Resume> 조회
    public List<Resume> getAllResumesByUser() {
        Long userId = getCurrentUserId();
        
        if (userId == null) {
            throw new IllegalStateException("No user logged in to view resumes");
        }

        if (!isOwner(userId)) {
            throw new IllegalStateException("Unauthorized access to view resumes");
        }

        return userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with ID: " + userId))
                .getResumes();
    }
}
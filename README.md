# 전체 개요  
### https://github.com/code-4-devdoc/dev-prod-test  
### https://github.com/code-4-devdoc/dev-prod-test-FE  
### https://github.com/code-4-devdoc/dev-prod-test-BE  
<br/>

---

# 메모
## Postman - Request Example
![postman_example](https://github.com/code-4-devdoc/devdoc-practice-React-SpringBoot-MySQL/assets/130027416/9a6c12f2-0d78-47fc-b416-b4442ff45711)
<br/>
<br/>

## Swagger-UI  
#### http://localhost:8080/swagger-ui/index.html  
`build.gradle` 파일에 추가 (Spring 3.3.0 기준)
> runtimeOnly 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
<br/>

## H2-Console
#### http://localhost:8080/h2-console  
- JDBC URL : jdbc:h2:mem:testdb  
- UserName : root  
- Password : root
<br/>

---

# DB 설게도  
- User : id(PK) email password List<Resume>
- Resume : id(PK) user_id(FK) title created_at List<Skill> List<Project>
- Skill : id resume_id(FK) title content
- Project : id resume_id(FK) title content period is_current

<br/>

---

# API 명세서  

GET     /api/auth/resumes
POST    /api/auth/resumes
GET     /api/auth/resumes/{resumeId}
POST    /api/auth/resumes/{resumeId}
DEL     /api/auth/resumes/{resumeId}
GET     /api/auth/resumes/{resumeId}/download
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

# API 명세서
### Resume 엔드포인트:

#### `GET /api/resumes`: 모든 Resume 조회 (resume_id만)
> (사용자의) 이력서 '목록' 조회

#### `POST /api/resumes`: 새로운 Resume 생성 (resume_id만)
> 이력서 테이블 생성

#### `GET /api/resumes/{id}`: 특정 Resume 조회 (Skill 및 Project 포함)
> 이력서{id}의 모든 테이블들 조회

#### `DELETE /api/resumes/{id}`: 특정 Resume 삭제 (연관된 Skill 및 Project 포함)
> 이력서{id}의 모든 테이블들 삭제
<br/>

### Skill 엔드포인트:

#### `GET /api/resumes/{resume_id}/skills`: 특정 Resume의 모든 Skill 조회
> 이력서{id}의 모든 Skill 테이블들 조회

#### `DELETE /api/resumes/{resume_id}/skills`: 득정 Resume의 모든 Skill 삭제
> 이력서 선택페이지에서 Skill 필드 비활성화 ~ 이력서{id}의 모든 Skill 테이블들도 함께 삭제? (필요없을지도)

#### `POST /api/resumes/{resume_id}/skills`: 새로운 Skill 생성
> 이력서{id}에 Skill 테이블 생성

#### `GET /api/resumes/{resume_id}/skills/{id}`: 특정 Skill 조회
> 이력서{id}의 Skill{id}의 테이블 조회 (얘도 필요없을지도)

#### `PUT /api/resumes/{resume_id}/skills/{id}`: 특정 Skill 수정
> 이력서{id}의 Skill{id}의 테이블 수정

#### `DELETE /api/resumes/{resume_id}/skills/{id}`: 특정 Skill 삭제
> 이력서{id}의 Skill{id}의 테이블 삭제
<br/>

### Project 엔드포인트:

#### `GET /api/resumes/{resume_id}/projects`: 특정 Resume의 모든 Project 조회
> 이력서{id}의 모든 Project 테이블들 조회

#### `DELETE /api/resumes/{resume_id}/projects`: 특정 Resume의 모든 Project 삭제
> 이력서 선택페이지에서 Project 필드 비활성화 ~ 이력서{id}의 모든 Project 테이블들도 함께 삭제? (필요없을지도)

#### `POST /api/resumes/{resume_id}/projects`: 새로운 Project 생성
> 이력서{id}에 Project 테이블 생성

#### `GET /api/resumes/{resume_id}/projects/{id}`: 득정 Project 조회
> 이력서{id}의 Project{id}의 테이블 조회 (얘도 필요없을지도)

#### `PUT /api/resumes/{resume_id}/projects/{id}`: 특정 Project 수정
> 이력서{id}의 Project{id}의 테이블 수정

#### `DELETE /api/resumes/{resume_id}/projects/{id}`: 특정 Project 삭제
> 이력서{id}의 Project{id}의 테이블 삭제

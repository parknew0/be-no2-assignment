## `application.properties` 설정안내

**DB 접속 정보는 Git에 커밋되지 않도록 `.gitignore`로 관리**하고 있습니다.  
아래 예시 파일을 참고하여 직접 `application.properties`를 만들어주세요.  
또는 `src/main/resources/application-example.properties` 파일의 비밀번호만 본인의 MySQL 비밀번호 변경해도 됩니다.  
⚠️ 주의: `resources`폴더에 `properties` 파일이 두개 이상 존재하면 오류가 나니 하나만 남겨주세요.

### 예시: `src/main/resources/application.properties`
```properties
db.url=jdbc:mysql://localhost:3306/schedule_db
db.username=root
db.password=본인비밀번호
```
---
# Lv 0. API 명세 및 ERD

## ✅ API 요약 표

| 기능             | Method | URL                      | Request Body     | Response Body | 상태 코드                               |
|------------------|--------|--------------------------|------------------|---------------|-------------------------------------|
| 일정 등록        | POST   | `/schedules`             | 일정 정보 + 비밀번호     | 등록 완료 메시지     | `201 Created`                       |
| 일정 목록 조회    | GET    | `/schedules`             | 없음               | 일정 목록         | `200 OK`                            |
| 일정 상세 조회    | GET    | `/schedules/{id}`        | 없음               | 일정 정보         | `200 OK`<br>`404 Not Found`         |
| 일정 수정        | PUT    | `/schedules/{id}`        | 수정할 일정 정보 + 비밀번호 | 수정 완료 메시지     | `200 OK`<br>`403 Forbidden`<br>`404 Not Found`           |
| 일정 삭제        | DELETE | `/schedules/{id}`        | 비밀번호             | 삭제 완료 메시지     | `204 No Content`<br>`403 Forbidden`<br>`404 Not Found`   |


## ✅ API 상세 목록

---

### 📌 일정 등록
- **Method**: POST
- **URL**: `/schedules`
- **Request Body**
  ```
  {
  "title": "회의 준비",
  "content": "회의 자료 작성",
  "writer": "홍길동",
  "password": "1234"
  }
  ```

- **Response Body**
  ```
  {
  "message": "일정이 성공적으로 등록되었습니다."
  }
  ```

- **Status Code**: `201 Created`

---

### 📌 일정 목록 조회
- **Method**: GET
- **URL**: `/schedules`

- **Response Body**
  ```
  [
  {
        "scheduleId": 1,
        "title": "수정된123 제목",
        "content": "JDBC 연결 테스트",
        "writer": "수정된 작성11자",
        "createdAt": "2025-05-25",
        "modifiedAt": "2025-05-26"
  },
  {
        "scheduleId": 9,
        "title": "테스트해보기",
        "content": "JDBC 연결 테스트",
        "writer": "박시녕2",
        "createdAt": "2025-05-26",
        "modifiedAt": "2025-05-26"
  }
  ]
  ```

- **Status Code**: `200 OK`

---

### 📌 일정 상세 조회
- **Method**: GET
- **URL**: `/schedules/1`

- **Response Body**
  ```
  {
  "scheduleId": 1,
  "title": "회의 준비",
  "content": "회의 자료 작성",
  "writer": "홍길동",
  "createdAt": "2025-05-26",
  "modifiedAt": "2025-05-26"
  }
  ```

- **Status Code**:
    - `200 OK`
    - `404 Not Found`

---

### 📌 일정 수정
- **Method**: PUT
- **URL**: `/schedules/1`

- **Request Body**
  ```
  {
  "title": "회의 준비 (수정)",
  "writer": "홍길동",
  "password": "1234"
  }
  ```

- **Response Body**
  ```
  {
  "message": "일정이 성공적으로 수정되었습니다."
  }
  ```

- **Status Code**:
    - `200 OK`
    - `403 Forbidden`
    - `404 Not Found`

---

### 📌 일정 삭제
- **Method**: DELETE
- **URL**: `/schedules/1`

- **Request Body**
  ```
  {
  "password": "1234"
  }
  ```

- **Response Body**:
  ```
  {
  "message": "일정이 성공적으로 삭제되었습니다."
  }
  ```

- **Status Code**:
    - `204 No Content`
    - `403 Forbidden`
    - `404 Not Found`

---


## 🔐 공통 에러 응답

| 상황               | 상태 코드        | 설명                             |
|--------------------|------------------|----------------------------------|
| 비밀번호 불일치     | `403 Forbidden`  | 요청한 비밀번호가 일치하지 않음   |
| 일정 없음          | `404 Not Found`  | 해당 ID의 일정이 존재하지 않음     |

## ✅ ERD 다이어그램!
![Blank diagram](https://github.com/user-attachments/assets/da816944-df97-4f2f-9455-d21c2bf9d6f3)


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

## ✅ API 목록

| 기능             | Method | URL                      | Request Body                | Response Body              | 상태 코드                               |
|------------------|--------|--------------------------|-----------------------------|-----------------------------|-------------------------------------|
| 일정 등록        | POST   | `/schedules`             | 일정 정보 + 비밀번호         | 등록된 일정 정보             | `201 Created`                       |
| 일정 목록 조회    | GET    | `/schedules`             | 없음                        | 일정 목록                    | `200 OK`                            |
| 일정 상세 조회    | GET    | `/schedules/{id}`        | 없음                        | 일정 정보                    | `200 OK`<br>`404 Not Found`         |
| 일정 수정        | PUT    | `/schedules/{id}`        | 수정할 일정 정보 + 비밀번호  | 수정 완료 메시지             | `200 OK`<br>`403 Forbidden`<br>`404 Not Found`           |
| 일정 삭제        | DELETE | `/schedules/{id}`        | 비밀번호                    | 삭제 완료 메시지             | `204 No Content`<br>`403 Forbidden`<br>`404 Not Found`   |
| 일정 상태 변경   | PATCH  | `/schedules/{id}/status` | 상태값 + 비밀번호            | 상태 변경 완료 메시지         | `200 OK`<br>`403 Forbidden`<br>`404 Not Found`           |



## 🔐 공통 에러 응답

| 상황               | 상태 코드        | 설명                             |
|--------------------|------------------|----------------------------------|
| 비밀번호 불일치     | `403 Forbidden`  | 요청한 비밀번호가 일치하지 않음   |
| 일정 없음          | `404 Not Found`  | 해당 ID의 일정이 존재하지 않음     |

## ✅ ERD 다이어그램!
![Blank diagram](https://github.com/user-attachments/assets/da816944-df97-4f2f-9455-d21c2bf9d6f3)


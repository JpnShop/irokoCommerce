# 🛍 오늘의 쇼텐 (직구 쇼핑몰)

<br>
<div align="center">
    <img src="https://img.shields.io/badge/IntelliJ IDEA-000000?style=for-the-badge&logo=IntelliJ IDEA&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=Spring&logoColor=white"/>
    <img src="https://img.shields.io/badge/spring boot-6DB33F?style=for-the-badge&logo=spring boot&logoColor=white"/>
    <img src="https://img.shields.io/badge/spring security-6DB33F?style=for-the-badge&logo=spring security&logoColor=white"/>
    <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=white"/>
</div>
<div align="center">
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=MySQL&logoColor=white"/>
    <img src="https://img.shields.io/badge/react-61DAFB?style=for-the-badge&logo=react&logoColor=black"/>
    <img src="https://img.shields.io/badge/html-E34F26?style=for-the-badge&logo=html5&logoColor=white"/>
    <img src="https://img.shields.io/badge/css-1572B6?style=for-the-badge&logo=css3&logoColor=white"/>
    <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"/>
</div>
<div align="center">
    <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"/>
    <img src="https://img.shields.io/badge/aws-232F3E?style=for-the-badge&logo=aws&logoColor=white"/>
</div> 

<br>



## 💁 프로젝트 정보

### 👉 미리보기 이미지 및 요청 URL  
- Cleint : https://finaloneul.netlify.app/
- Server : https://final.oneulistore.click/swagger-ui/
<br><br>
![스크린샷 2022-10-21 오후 5 13 59](https://user-images.githubusercontent.com/57162810/197147285-04d0e8d4-715e-4237-bc98-1e3536765237.png)

### 🏋️‍♀️ 프로젝트 기간
- 22/09/02 ~ 진행 중
### 💬 프로젝트 설명
> 한류의 인기가 높아지고, 그에 따른 한국 제품들의 일본 소비가 증가함에 따라 제작하게 된 일본 역직구 쇼핑몰 제작 사이트입니다.


## 🔙 팀구성 및 담당 역할
### 🧑🏻‍💻 팀장 이남대(@Namdea_lee)
- AWS EC2 서버 배포
- AWS RDS 연동
- Jenkins, Docker 연동
- 상품 주문, 주문 조회 기능 구현
- 결제 요청, 조회, 취소 기능 구현
### 🧑🏻‍💻 팀원 이중후(@wndgndi)
- JWT와 시큐리티를 사용한 로그인/회원가입 기능 구현
- 구글 로그인 연동 구현
- 아이디 비밀번호 찾기 기능 구현
- 임시 비밀번호 안내 메일 발송 기능 구현
- 스웨거 구현
### 👩🏻‍💻 팀원 위준우(@wijoonwu)
- 회원별 관심상품 등록,삭제,조회 기능 구현
- 회원별 장바구니 등록,삭제, 조회 기능 구현
- 공지사항 글 작성, 수정, 삭제, 조회 기능 구현 
- 리뷰 게시판 글 작성, 사진 등록, 수정, 삭제, 조회 기능 구현
- 매거진 등록, 수정, 삭제, 조회 기능 구현

## 💻 개발 환경
- IDE : intelliJ IDEA
- JDK : java 11
- Project : Gradle 7.5
- Spring Boot : 2.7.3
- DataBase : MySQL 8.0.30
- EC2 Instance free tier
- Amazon Linux2 kerner 5.10

## 🌱 Dependency
- Spring Web
- Spring Dev Tools
- Lombok
- Spring Data JPA
- Spring Security
- Swagger
- JWT


## 💾 DB 다이어그램
![스크린샷 2022-10-21 오후 5 23 53](https://user-images.githubusercontent.com/57162810/197149208-05de2f25-fcc4-4d45-8a17-fc9844e215a1.png)

## 🕋 패키지 구조 
- main
    - biz
        - domain : Entity
        - repository
        - service
    - web
        - controller
        - dto : Request, Response 객체

## 📡 API 명세서
- https://www.notion.so/API-5982ec0967a945a1bdeb30c8e2636bf3
### ✅ 로그인/회원가입
- `GET` /auth/Glogin
- `POST` /auth/login
- `POST`  /auth/reissue
- `POST` /auth/signup
### 🧺 장바구니
- `DELETE` /carts
- `DELETE` /carts/empty
- `POST` /carts
- `PUT` /carts
- `GET` /carts
### ⭐️ 관심상품
- `DELETE` /favorites
- `DELETE` /favorites/empty
- `POST` /favorites
- `GET` /favorites
### 👩🏻‍🦱 회원관리
- `GET` 
### 🎁 상품관리
- `GET` /products
- `PUT` /products/{id}
- `DELETE` /products/{id}
- `POST` /products/list
- `POST` /products/save
### QNA 게시판
### 공지사항 게시판
### 리뷰 게시판
### 매거진
### 주문
### 카테고리

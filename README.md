# :clipboard: Pre-OnBoarding Assignment (한 달 인턴 온 보딩 과제)

<strong>Spring Boot에서 Spring Security와 JWT를 기반으로 회원가입, 로그인을 구현하는 과제입니다!</strong>

<br/>

# :memo: 요구 사항

- [ ]  Spring Security에 대한 이해와 Filter에 대한 이해
- [ ]  JWT에 대한 기본적인 이해와 구체적인 알고리즘의 이해
- [ ]  토큰 발행/검증에 대한 테스트 코드 작성하기
- [ ]  GitHub에 PR 날려보기
- [ ]  리뷰 바탕으로 개선하기
- [ ]  Swagger UI로 접속 가능하게 하기
- [ ]  AWS EC2에 배포해보기

<br/>

# :books: 요구 사항에 따른 구현 사항 

- Spring Security와 JWT를 이용한 회원가입/로그인 구현
  - 회원가입 및 로그인이 구현되어 있습니다.
  - Role은 USER, ADMIN 2개만 있습니다.
  - /api/v1/member/is-logged-in에는 유효한 Token이 있는 경우, 즉 로그인된 경우에만 "성공적으로 로그인 되었습니다!"가 반환됩니다.
  - /api/v1/admin/is-admin에는 ADMIN 역할을 가진 경우에만 "당신은 관리자입니다!"가 반환됩니다.

- 토큰 발행/검증에 대한 테스트 코드 작성하기
  - 테스트 시나리오 5개
    - generateAccessToken()이 유효한 JWT 토큰을 생성한다
    - validateToken()이 유효한 JWT 토큰에 대해서는 Success를 반환한다
    - validateToken()이 유효하지 않은 JWT 토큰에 대해서는 Failure를 반환한다
    - validateToken()은 만료된 JWT에 대해 Failure를 반환한다
    - generateAccessToken()으로 생성된 토큰은 지정된 만료시간이 적용되어야 한다

- AWS EC2에 배포해보기 (Swagger UI 주소)
  - 배포 주소: http://43.203.254.207:8080/swagger-ui/index.html 

<br/>

<strong>API 명세서</strong>

|Command|Method|성공 Status Code|URI|
|---|---|:---:|---|
|회원가입|`POST`|`201`|/api/v1/member/sign-up|
|로그인|`POST`|`200`|/api/v1/member/login|
|로그인 여부 확인|`GET`|`200`|/api/v1/member/is-logged-in|
|관리자 여부 확인|`GET`|`200`|/api/v1/admin/is-admin|

<br/>

## :pushpin: 환경설정

|           |                                                             Tool & Version                                                             |
|:---------:|:--------------------------------------------------------------------------------------------------------------------------------------:|
| Language  |              <img src="https://img.shields.io/badge/Kotlin-ver 1.9-7F52FF?style=flat-squre&logo=Kotlin&logoColor=white"/>              |
|    IDE    |            <img src="https://img.shields.io/badge/Intellij%20IDEA-000000?style=flat-squre&logo=intellijidea&logoColor=white"/>          |
|    SDK    | <img src="https://img.shields.io/badge/Eclipse%20Temurin-ver 17.0.12-FF1464?style=flat-squre&logo=eclipseadoptium&logoColor=white"/> | 
| Framework |       <img src="https://img.shields.io/badge/Spring%20Boot-ver 3.3.3-6DB33F?style=flat-squre&logo=springboot&logoColor=white"/>        |
|    JWT    |         <img src="https://img.shields.io/badge/jjwt-ver 0.12.5-000000?style=flat-square&logo=jsonwebtokens&logoColor=white"/>          |

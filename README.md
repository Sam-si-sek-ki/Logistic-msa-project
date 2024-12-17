
<br>

# Logistic-msa-project

### 2차 대규모 AI 시스템 설계 프로젝트

<br><br>

## 👨‍👩‍👧‍👦 Our Team

|고경호|김해나|손예지|이건우|
|:---:|:---:|:---:|:---:|
|[@yoaruku](https://github.com/yoaruku)|[@gogohaena](https://github.com/gogohaena)|[@handyejee](https://github.com/handyejee)|[@geonuu09](https://github.com/geonuu09)|
|Hub|Auth,infra|Delivery, Company|Product, Order|

<br>

### [🍛 프로젝트 노션 바로가기](https://teamsparta.notion.site/3-ff76eca36a8143999f5d0f4a8deee0a4)

<br><br>




## 프로젝트 기능

### 🔍 검색 기능

> * QueryDSL을 활용하여 동적 쿼리작성이 가능하도록 구현하였습니다.
> * 각 Entity의 내용을  ID로 검색하거나 저장 정보 기준 동적 쿼리조회가 가능합니다.

<details>
<summary>미리보기</summary>
<div markdown="1">

![singleSearch.png](/infra/images/singleSearch.png)
![listSearch.png](/infra/images/listSearch.png)
 <br>
</div>
</details>

### 📬 슬랙 메세지 전송 기능

> * 배송 생성시 사용자 헤더값으로 slackId 조회 해오도록 구현
> * 배송 경로 생성시 notification service 에 알림 메세지 송신하도록 구현
<details>
<summary>미리보기</summary>
<div markdown="1">

![slackMessage.png](/infra/images/slackMessage.png)
<br>
</div>
</details>

## 적용 기술

### ◻ QueryDSL

> 정렬, 검색어 등에 따른 동적 쿼리 작성을 위하여 QueryDSL 도입하여 활용했습니다.

### ◻ Naver Map API

> 출발지의 좌표값과 목적지의 좌표값을 입력해 예상 시간과 예상 거리를 받아 사용했습니다.

### ◻ Swagger

> 프론트엔드와 정확하고 원활한 소통을 위하여 스웨거를 도입하여 적용하였습니다.         
> Swagger Link

<br><br>



## ⚙ Development Environment

`Java 17` `SpringBoot 3.4.0` `QueryDSL 5.0`  `Spring Cloud` `docker-compose`


<br><br>

## 🚨 Trouble Shooting

#### 인가 권한체크 문제 [WIKI보기](https://github.com/Sam-si-sek-ki/logistic-msa-project/wiki/%EC%9D%B8%EA%B0%80-%EA%B6%8C%ED%95%9C%EC%B2%B4%ED%81%AC-%EB%AC%B8%EC%A0%9C)

#### CreateBy, UpdateBy 동작문제 [WIKI보기](https://github.com/Sam-si-sek-ki/logistic-msa-project/wiki/CreateBy,-UpdateBt-%EB%8F%99%EC%9E%91-%EB%AC%B8%EC%A0%9C)

#### JsonIgnore 사용 시 Dto 출력에서 없어지는 현상 [WIKI보기](https://github.com/Sam-si-sek-ki/logistic-msa-project/wiki/JsonIgnore-%EC%82%AC%EC%9A%A9-%EC%8B%9C-Dto-%EC%B6%9C%EB%A0%A5%EC%97%90%EC%84%9C-%EC%97%86%EC%96%B4%EC%A7%80%EB%8A%94-%ED%98%84%EC%83%81)


<br><br>

## 🌐 Architecture



<br>

## 📋 ERD Diagram
![erd.png](/infra/images/erd.png)


<br>

## 📝 Technologies & Tools (BE) 📝

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/> <img src="https://img.shields.io/badge/SpringSecurity-6DB33F?style=for-the-badge&logo=SpringSecurity&logoColor=white"/> <img src="https://img.shields.io/badge/JSONWebToken-000000?style=for-the-badge&logo=JSONWebTokens&logoColor=white"/>

<img src="https://img.shields.io/badge/PostgreSQL-4479A1?style=for-the-badge&logo=PostgreSQL&logoColor=white"/> <img src="https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=black"/> <img src="https://img.shields.io/badge/Gradle-02303A?style=for-the-badge&logo=Gradle&logoColor=white"/> 

<img src="https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=docker&logoColor=white"/> <img src="https://img.shields.io/badge/git-F05032?style=for-the-badge&logo=git&logoColor=white"/> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"/>  

<img src="https://img.shields.io/badge/IntelliJIDEA-000000?style=for-the-badge&logo=IntelliJIDEA&logoColor=white"/>  <img src="https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=Postman&logoColor=white"/> <img src="https://img.shields.io/badge/Notion-000000?style=for-the-badge&logo=Notion&logoColor=white"/> <img src="https://img.shields.io/badge/Slack-4A154B?style=for-the-badge&logo=slack&logoColor=white"/> 

<br><br><br><br>
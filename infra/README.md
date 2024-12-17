# 환경 설정 방법

## 디렉토리 생성
컨테이너에서 생성되는 데이터의 보존을 위해 로컬에 디렉토리를 생성하고 컨테이너 볼륨으로 마운트 합니다. 아래와 같이 디렉토리(폴더)를 생성해주세요.
~~~
logistic-msa-project
└── infra
    ├── postgres-data
    ├── redis-data
    └── zipkin-data
~~~

## 컨테이너 실행/종료
~~~
# infra 디렉토리로 이동
cd {각자의 작업 위치}/logistic-msa-project/infra

### PostgreSQL
# 종료
docker-compose -f docker-compose-postgres.yml down
# 실행
docker-compose -f docker-compose-postgres.yml up -d

### Redis
# 종료
docker-compose -f docker-compose-redis.yml down
# 실행
docker-compose -f docker-compose-redis.yml up -d

### Zipkin
# 종료
docker-compose -f docker-compose-zipkin.yml down
# 실행
docker-compose -f docker-compose-zipkin.yml up -d
~~~

## 컨테이너 확인
~~~
docker ps -a

(출력 예)
CONTAINER ID   IMAGE             COMMAND                   CREATED         STATUS         PORTS                     NAMES
9f13ee4c8344   postgres:latest   "docker-entrypoint.s…"   5 seconds ago   Up 5 seconds   0.0.0.0:15432->5432/tcp   postgres
~~~

## 기타 명령어
~~~
# 컨테이너 전체 종료 및 삭제
docker rm -f $(docker ps -aq)
~~~

# DB 연결 정보
### PostgreSQL
- Host : `localhost`
- Port : `15432`
- Database : `sparta_{service}`
- Username : `sparta_{service}`
- Password : `sparta_{service}`
~~~
- {service} : 
  - auth
  - company
  - delivery
  - hub
  - notification
  - order
  - product
~~~

# DB 리셋
~~~
# 스크립트 실행 (데이터 폴더 재생성 및 컨테이너 재시작) 
./reset-postgres.sh
~~~

# Redis Stack
### Redis Server
- Host : localhost
- Port : 6379
### Redis Insight
- Host : localhost
- Port : 8001

# Zipkin
- Host : localhost
- Port : 9411
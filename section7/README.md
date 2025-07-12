---

# Section 7:  Usign MYSQL Database inside microservices

## Overview

Setting up and running MySQL database containers for microservices using Docker.

---

W tej sekcji 7 zmieniamy h2 na mysql

Mozna utworzyc mysql w dockerze taką komendą
docker run -p 3306:3306 --name accountsdb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=accountsdb -d mysql:8.0.36

zminaimey appplication.yml zeby pasowal do mysql
datasource:
url: jdbc:mysql://localhost:3306/accountsdb
username: root
password: root
jpa:

no i mozemy odpalic bazydanych i mikroserwisy
generalnie zawsze jak usuniemy kontener tracimy dane,
ale w srodowisk produkcyjnym tworszymy foldery volume ktore sprawia ze nigdy nie stracyimy danych


okej to teraz dodajemy bazy danych do docker-compose.yml

accountsdb:
image: mysql
container_name: accountsdb
ports:
- 3306:3306
healthcheck:
test: ["CMD","mysqladmin","ping","-h","localhost"]
      
dodajemy healthcheck i takich informajcji jakei sa healthchecki mozemy szukac online 

no i jak juz zrobimy docker-compose mozemy odpalic nasze kontenery

            - 3308:3306
a jak chcemy sie polaczyc z baza danych  SPRING_DATASOURCE_URL: jdbc:mysql://cardsdb:3306/cardsdb 
wchodzimy w port wewnetrzny i szukamy po prostu kontenera po jego nazwie 
#FROM build_container AS builder



FROM openjdk:17-alpine as final


RUN apk add --no-cache maven

WORKDIR /build
COPY . .

RUN mvn clean install

#RUN ls -lart
#RUN ls -lart target/classes/sql

#COPY /build/target/classes/sql/dump_inicial.sql /docker-entrypoint-initdb.d/

EXPOSE 8080
EXPOSE 9000

# Instale o cliente MySQL
RUN apk add --no-cache mysql-client

# Execute o script SQL na inicialização
#CMD ["mysql", "-u", "root", "-p", "rootroot", "/build/target/classes/sql/dump_inicial.sql"]
#RUN mysql -u fiap -h mariadb-techchallenge -pfiap tech-challenge < /build/target/classes/sql/dump_inicial.sql
#RUN mysql -u fiap -pfiap -P3336 tech-challenge -e 'CREATE TABLE IF NOT EXISTS cliente (cpf VARCHAR(14) NOT NULL PRIMARY KEY, nome VARCHAR(255) NOT NULL, email VARCHAR(255) NOT NULL,  telefone VARCHAR(20) NOT NULL);'
#RUN mysql -u fiap -pfiap -P3336 tech-challenge -e "INSERT INTO cliente (cpf, nome, email, telefone) VALUES('12345678900001', 'Cliente 1', 'cliente1@email.com', '11-987654321'),('98765432100002', 'Cliente 2', 'cliente2@email.com', '21-123456789'),('00000000000003', 'Cliente 3', 'cliente3@email.com', '41-345678901');"




#RUN mysql -u fiap -h mariadb-techchallenge -pfiap tech-challenge < /build/target/classes/sql/dump_inicial.sql

ENTRYPOINT ["java", "-jar", "/build/target/fiapfood-0.0.1-SNAPSHOT.jar"]



#COPY --from=builder /your/app/target/your-app.jar /app/your-app.jar
#ENTRYPOINT ["java", "-jar", "/app/your-app.jar"]

#WORKDIR /build

#COPY . .


#RUN mvn clean install -X
#RUN mvn install -DskipTests -X
#RUN cd ..

#COPY pom.xml ./
#COPY src src
#RUN mvn package -DskipTests

#RUN ls -lart > docker.txt
#CMD cat docker.txt


#COPY . /build/target/simple-server.jar

#EXPOSE 8080
#EXPOSE 9000

#ENTRYPOINT ["java", "-jar", "/app/simple-server.jar"]










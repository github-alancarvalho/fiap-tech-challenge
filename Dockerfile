
FROM openjdk:17-alpine as final


RUN apk add --no-cache maven

WORKDIR /build
COPY . .

RUN mvn clean install


EXPOSE 8080
EXPOSE 9000

# Instale o cliente MySQL
RUN apk add --no-cache mysql-client


ENTRYPOINT ["java", "-jar", "/build/target/fiapfood-0.0.1-SNAPSHOT.jar"]







FROM maven:3.8.3-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17
COPY --from=build /target/SubSkill-0.0.1-SNAPSHOT.jar app.jar
COPY /src/main/resources/application.properties /usr/app/

EXPOSE 8080

ENTRYPOINT ["java","-jar","app.jar"]
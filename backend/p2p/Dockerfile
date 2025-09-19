FROM maven:3.8.6-openjdk-18 AS build
WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

FROM amazoncorretto:21
WORKDIR /app

# Maven Shade Plugin creates JAR with same name as original (not -jar-with-dependencies)
COPY --from=build /build/target/p2p-1.0-SNAPSHOT.jar app.jar

# Debug: list files to verify (removed 'file' command as it's not available in base image)
RUN ls -la /app/ && echo "JAR info:" && ls -la /app/app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

FROM adoptopenjdk/openjdk11:alpine-jre

# Spring groups
RUN addgroup -S spring && adduser -S spring -G spring

# Spring user
USER spring:spring

# Refer to Maven build -> finalName
ARG JAR_FILE=build/libs/*.jar

# cd /opt/app
WORKDIR /opt/app

# cp build/libs/*.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# Expose port 8080
EXPOSE 8080

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","app.jar"]
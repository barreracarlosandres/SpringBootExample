FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ADD build/libs/SpringBootExample-1.0.jar /app.jar
ADD build/libs/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar
COPY start-app.sh start-app.sh
# ARG JAR_FILE=build/libs/SpringBootExample-1.0.jar
# COPY ${JAR_FILE} SpringBootExample-1.0.jar
# ARG JAR_FILE=build/libs/opentelemetry-javaagent.jar
# COPY ${JAR_FILE} opentelemetry-javaagent.jar
ENTRYPOINT ["sh","start-app.sh"]
# ENTRYPOINT ["java","-jar","/app.jar"]
# ENTRYPOINT ["java","-jar","-javaagent:/opentelemetry-javaagent.jar","/app.jar"]


# FROM eclipse-temurin:11-jre
#
# ADD build/libs/opentelemetry-examples-javaagent-*-SNAPSHOT.jar /app.jar
# ADD build/otel/opentelemetry-javaagent.jar /opentelemetry-javaagent.jar
#
# ENTRYPOINT java -jar -javaagent:/opentelemetry-javaagent.jar app.jar
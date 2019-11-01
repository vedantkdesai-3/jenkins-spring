FROM openjdk:8-jdk-alpine
ADD target/student-service-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8085 

HEALTHCHECK --interval=5s --timeout=3s CMD curl --fail http://localhost:8085/health || exit 1

ENTRYPOINT ["java","-jar","app.jar"]
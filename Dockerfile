FROM maven:3.6.3-jdk-11 as maven
COPY ./ ./maven-app
WORKDIR /maven-app 
RUN mvn package -Dmaven.test.skip=true

# RUN pwd
# RUN ls
# RUN ls target
# RUN java -jar /target/*.jar
FROM openjdk:11
WORKDIR /maven-app
COPY --from=maven /maven-app/target/*.jar ./app.jar 
Run pwd
Run ls
ENTRYPOINT ["java","-jar","./app.jar"]

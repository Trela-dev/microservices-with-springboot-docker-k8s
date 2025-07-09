#Starts with a base image containing Java runtime
FROM openjdk:21-jdk-slim

#Information arount who maintains the image
LABEL maintainer="marcin.trela.dev@gmail.com"

# Add the application's jar to the container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

#Execute the application
ENTRYPOINT ["java", "-jar","accounts-0.0.1-SNAPSHOT.jar"]

#Creating image
# docker build . -t treladev/accounts:s4

#Running container from image
# docker run -p 8080:8080 treladev/accounts:s4

#Viewing running containers
# docker ps

#Viewing all containers
# docker ps -a

#You can also view all files in the Docker Desktop UI

#starting the container
# docker start container_id

#Stopping the container
# docker stop container_id

#You may run multiple containers using same command as running docker  but on different ports
# docker run -p 8081:8080 treladev/accounts:s4
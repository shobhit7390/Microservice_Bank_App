# Start with the base image containing Java runtime
FROM openjdk:21-jdk-slim

# Information around who maintains the image
MAINTAINER shobhit

# Add the application's jar to the container
COPY target/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/accounts-0.0.1-SNAPSHOT.jar"]


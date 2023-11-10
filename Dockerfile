#système léger qui contient jdk
FROM openjdk:11

# Set the Nexus URL and the artifact path
ARG NEXUS_URL=http://192.168.176.1:8081/repository/maven-releases
ARG ARTIFACT_PATH=com/example/stationSki/1.1.0/stationSki-1.1.0.jar

# Create a directory to store the downloaded JAR
RUN mkdir /app
RUN touch Test

# Download the JAR from Nexus and copy it into the container
RUN wget -O /app/stationSki-1.1.0.jar $NEXUS_URL/$ARTIFACT_PATH

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "/app/stationSki-1.1.0.jar"]
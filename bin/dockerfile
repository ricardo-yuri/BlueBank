FROM openjdk
workdir /app
COPY target/bluebank-0.0.1-SNAPSHOT.jar /app/bluebank.jar
ENTRYPOINT ["java","-jar", "bluebank.jar"]
EXPOSE 5000
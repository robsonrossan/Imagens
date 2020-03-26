FROM openjdk:11-jdk-slim
VOLUME /tmp
ADD app/target/CoreJasmin-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8181
RUN sh -c 'touch /app.jar'
CMD ["jasmin", "run", "--host", "0.0.0.0"]
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app.jar"]

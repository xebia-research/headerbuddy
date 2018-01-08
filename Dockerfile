FROM java:8
ADD ./target/headerbuddy-0.0.1-SNAPSHOT.jar //
ENTRYPOINT ["java", "-jar", "/headerbuddy-0.0.1-SNAPSHOT.jar"]

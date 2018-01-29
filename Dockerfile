FROM java:8
ADD ./target/headerbuddy-3.0.0.jar //
ENTRYPOINT ["java", "-jar", "/headerbuddy-3.0.0.jar"]

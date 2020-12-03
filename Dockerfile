FROM openjdk:11
RUN chmod +x gradlew ; ./gradlew --no-daemon build
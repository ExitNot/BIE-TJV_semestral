FROM openjdk:11
COPY . /build
WORKDIR /build

RUN chmod +x gradlew ; ./gradlew --no-daemon build
FROM maven:3.8.1-adoptopenjdk-16 as build
COPY . /usr/src/empik-task
WORKDIR /usr/src/empik-task
RUN mvn clean package
FROM adoptopenjdk/openjdk16:alpine-jre
RUN addgroup -S empik && adduser -S empik -G empik
RUN mkdir -p /files &&  \
        chown -R empik:empik /files
USER empik:empik
VOLUME /files
WORKDIR /app
COPY --from=build /usr/src/empik-task/target/*.jar /app/app.jar
EXPOSE 8489
ENTRYPOINT ["java", "-jar", "app.jar"]
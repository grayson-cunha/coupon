FROM gradle:9.2.1-jdk21-alpine AS build
WORKDIR /home/app

COPY . .

RUN gradle clean bootJar --no-daemon

FROM amazoncorretto:21-alpine
WORKDIR /app

COPY --from=build /home/app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]%
FROM gradle:8.5-jdk21 AS build
WORKDIR /home/gradle/src

COPY --chown=gradle:gradle build.gradle settings.gradle ./

RUN gradle dependencies --no-daemon || true

COPY --chown=gradle:gradle . .

# 라이브러리 포함된 Jar 생성
RUN gradle shadowJar --no-daemon -x test

FROM openjdk:21-slim
EXPOSE 3939
mkdir /app

COPY --from=build /home/gradle/src/build/libs/*-all.jar /app/app.jar

VOLUME /app/data

ENTRYPOINT ["java", "-jar", "/app/app.jar"]

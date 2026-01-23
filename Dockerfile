# 1. Используем образ с JDK для сборки
FROM eclipse-temurin:21-jdk AS build

WORKDIR /app

# Копируем gradle файлы
COPY build.gradle settings.gradle gradlew ./
COPY gradle gradle

# Делаем gradlew исполняемым
RUN chmod +x gradlew

# Копируем исходники
COPY src src

# Собираем jar
RUN ./gradlew clean build -x test

# -----------------------
# 2. Минимальный образ для запуска
FROM eclipse-temurin:21-jre

WORKDIR /app

# Создаем пользователя
RUN addgroup --system spring && adduser --system spring --ingroup spring

# Копируем jar из build стадии
COPY --from=build /app/build/libs/backend-0.0.1-SNAPSHOT.jar app.jar
COPY wait-for-it.sh start.sh ./
RUN chmod +x wait-for-it.sh start.sh

USER spring:spring
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75.0"
EXPOSE 8080

ENTRYPOINT ["./start.sh"]

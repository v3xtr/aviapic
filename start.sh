#!/bin/sh
# Скрипт ждёт пока Postgres будет доступен, затем запускает Spring Boot

set -e

# Ждём Postgres
./wait-for-it.sh postgres:5432 --timeout=30 --strict -- echo "Postgres is up"

# Запускаем приложение с Java опциями
exec java $JAVA_OPTS -jar app.jar

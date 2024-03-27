# Проект "Adapter"

[ссылка на задание](task/README.md)

## Описание

Этот проект представляет собой микросервис "Adapter", который принимает сообщение из сервиса "Service A", производит необходимые преобразования и передает его в сервис "Service B".

## Технологии

- Java
- Spring Boot
- Maven
- RESTful API
- WebClient
- Docker

## Зависимости

Чтобы собрать проект, у вас должны быть установлены:
- Java Development Kit (JDK)
- Apache Maven
- Docker

## Сборка проекта

Для сборки проекта выполните следующие шаги:

1. Клонируйте репозиторий на локальную машину:

```bash
git clone https://github.com/AlexPolugrudov/test-project.git
```
2. Перейдите в каталог проекта:
```bash
cd test-project
```
3. Соберите проект с помощью Maven:
```bash
mvn clean install
```

### Сборка Docker-образа

Для сборки Docker-образа выполните следующие шаги:

1. Клонируйте репозиторий на локальную машину:

```bash
git clone https://github.com/AlexPolugrudov/test-project.git
```
2. Перейдите в папку с Docker-файлом:
```bash
cd test-project/docker
```
3. Соберите Docker-образ:
```bash
docker run build -t test-project
```
## Запуск

Для запуска проекта выполните следующие команды:
```bash
cd target
java -jar test-project.jar
```
### Запуск Docker-образа
```bash
docekr run -p port 8080:8080 test-project
```

# API
Докуметация по API доступна по адресу:
- Локально: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- В Docker-контейнере: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

Если приложение запущено в контейнере, замените "localhost" на IP-адрес вашего Docker-хоста

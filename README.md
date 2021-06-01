# empik-task

Recruitment task for Empik.

## Build locally - requirements

- Java 16
- Maven

## Build and run app with docker
- Build `docker build . --tag empik-task`
- Run (set server port in docker container and bind it to host port eg. 8489) `docker run -p 8489:8489 empik-task`

## API Reference

```
{{address}}:8489/swagger-ui.html
{{address}}:8489/api-docs
```
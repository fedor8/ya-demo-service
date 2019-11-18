FROM openjdk:11-jdk-slim
VOLUME /tmp
ARG DEPENDENCY=target/dependency
COPY ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY ${DEPENDENCY}/META-INF /app/META-INF
COPY ${DEPENDENCY}/BOOT-INF/classes /app

EXPOSE 8002
EXPOSE 8090

ENTRYPOINT ["java","-cp","app:app/lib/*","com.example.demo.DemoApplication"]
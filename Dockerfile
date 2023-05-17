FROM arm64v8/openjdk:11-jdk-slim as builder

# Copy local code to the container image.
WORKDIR /app
VOLUME /tmp

# 将jar包添加到容器中并更名为zzyy_docker.jar

ADD user-center-backend-0.0.1-SNAPSHOT.jar user-center-backend.jar

# 运行jar包

RUN bash -c 'touch /user-center-backend.jar'

# Run the web service on container startup.
CMD ["java","-jar","/user-center-backend.jar","--spring.profiles.active=prod"]


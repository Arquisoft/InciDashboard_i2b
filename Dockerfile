MAINTAINER Alejandro Gonzalez Hevia <alejandrgh11@gmail.com>
FROM maven:3.5-jdk-8-alpine
WORKDIR /usr/src/InciDashboard_i2b
COPY . /usr/src/InciDashboard_i2b/
RUN if test ! -e target/InciDashboard_i2b-0.0.1.jar; then mvn package -Dmaven.test.skip=true; fi
EXPOSE 8082
CMD ["java", "-jar", "InciDashboard_i2b-0.0.1-SNAPSHOT.jar", "--spring.kafka.bootstrap-servers=kafka:9092"]

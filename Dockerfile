FROM maven:3.5-jdk-8-alpine
MAINTAINER Alejandro Gonzalez Hevia <alejandrgh11@gmail.com>
WORKDIR /usr/src/InciDashboard_i2b
COPY . /usr/src/InciDashboard_i2b/
RUN mvn package -Dmaven.test.skip=true
EXPOSE 8082
CMD ["java", "-jar", "target/InciDashboard_i2b-0.0.1.jar", "--spring.data.mongodb.host=mongo_incidents", "--spring.kafka.bootstrap-servers=kafka:9092"]

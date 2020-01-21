FROM java:8-jdk-alpine

COPY ./target/account.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch account.jar'

ENTRYPOINT ["java", "-jar", "account.jar"]
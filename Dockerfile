FROM debian:stable-slim
RUN apt update && apt -y upgrade
RUN apt install -y openjdk-17-jdk maven
RUN apt clean
RUN rm -rf /var/lib/apt/lists/*

WORKDIR /app
COPY . .

RUN mvn clean install -f ./pom.xml

RUN ls ./target
EXPOSE 4000
ENTRYPOINT ["java","-jar","-Dfile.encoding=UTF-8","./target/app.jar"]

FROM adoptopenjdk/openjdk14:alpine-slim
EXPOSE 1234
WORKDIR /player
ADD target/scala-2.13/player-assembly-SAR-6.jar /player
ENV DOCKERENV="TRUE"
CMD java -jar player-assembly-SAR-6.jar

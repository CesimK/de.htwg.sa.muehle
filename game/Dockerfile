FROM adoptopenjdk/openjdk14:alpine-slim
EXPOSE 54251
WORKDIR /game
ADD target/scala-2.13/game-assembly-SAR-6.jar /game
ENV DOCKERENV="TRUE"
CMD java -jar game-assembly-SAR-6.jar

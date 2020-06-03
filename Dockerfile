FROM adoptopenjdk/openjdk14:slim
EXPOSE 1233
WORKDIR /
ADD target/scala-2.13/muehle-assembly-SAR-6.jar /
ADD ./wait-for-it.sh /
CMD java -jar muehle-assembly-SAR-6.jar
ENV DOCKERENV="TRUE"

FROM hseeberger/scala-sbt
WORKDIR /muehle
ADD . /muehle
CMD sbt run

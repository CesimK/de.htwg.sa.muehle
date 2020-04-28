name := "de.htwg.se.muehle"

version := "0.1"

scalaVersion := "2.12.8"

libraryDependencies += "org.scalactic" %% "scalactic" % "3.0.5"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % "test"

libraryDependencies += "org.scala-lang.modules" % "scala-swing_2.12" % "2.0.3"

libraryDependencies += "com.google.inject" % "guice" % "4.1.0"

libraryDependencies += "net.codingwell" %% "scala-guice" % "4.1.0"

libraryDependencies += "org.scala-lang.modules" % "scala-xml_2.12" % "1.0.6"
libraryDependencies += "com.typesafe.play" %% "play-json" % "2.6.6"

coverageExcludedFiles := "GridCreateStrategy.scala;ControllerStateStatus.scala;ControllerStateActive.scala;ControllerStateStatusSpec.scala;GridCreateGridStrategySpec.scala"
coverageExcludedPackages := "GridCreateStrategy.scala;ControllerStateStatus.scala;ControllerStateActive.scala;ControllerStateStatusSpec.scala;GridCreateGridStrategySpec.scala"
coverageEnabled := true

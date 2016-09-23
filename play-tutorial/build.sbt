
name := "play-tutorial"

scalaVersion := "2.11.8"

// Play
lazy val root = (project in file(".")).enablePlugins(PlayScala)

organization := "PlayTutorial"

// append options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked", "-language:_")

libraryDependencies ++= List(
  jdbc,
  cache,
  ws,
  /*"com.typesafe.slick" %% "slick" % "3.1.1",*/
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0",
  "org.slf4j" % "slf4j-nop" % "1.7.10",
  "com.h2database" % "h2" % "1.4.192",
  /*"org.scalatest" %% "scalatest" % "2.2.4" % "test",*/
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
)
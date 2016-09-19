name := """scala-tutorial"""

version := "1.0"

scalaVersion := "2.11.8"
lazy val akkaVersion = "2.4.9"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.2.4" % "test",
  "com.typesafe.akka" %% "akka-actor" % akkaVersion
)

// append options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked", "-feature")


fork in run := true

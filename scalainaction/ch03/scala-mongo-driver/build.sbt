name := "scalainaction ch03 scala-mongo-driver"

scalaVersion := "2.11.8"

organization := "ScalaInAction"
libraryDependencies += "org.mongodb" % "mongo-java-driver" % "2.10.1"
// append options passed to the Scala compiler
scalacOptions ++= Seq("-deprecation", "-unchecked", "-language:_")
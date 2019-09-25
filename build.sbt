name := """toDo"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.0"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"

// https://mvnrepository.com/artifact/org.scalikejdbc/scalikejdbc_2.11

// https://mvnrepository.com/artifact/org.postgresql/postgresql

//下にまとめました
// libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1211"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "9.4.1211",
  "org.scalikejdbc" %% "scalikejdbc" % "3.3.5",
  "org.scalikejdbc" %% "scalikejdbc-config" % "3.3.5",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.7.1-scalikejdbc-3.3"
)

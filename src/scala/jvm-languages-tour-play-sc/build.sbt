name := """jvm-languages-tour-play-sc"""
organization := "tech.leafwinglabs"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.13.14"

libraryDependencies += guice
libraryDependencies ++= Seq(
  "org.playframework" %% "play-slick" % "6.1.1",
  "org.playframework" %% "play-slick-evolutions" % "6.1.1",
  "org.postgresql" % "postgresql" % "42.2.23",
  "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.1" % Test
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "tech.leafwinglabs.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "tech.leafwinglabs.binders._"

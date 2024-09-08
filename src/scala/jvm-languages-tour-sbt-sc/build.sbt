ThisBuild / scalaVersion := "3.4.0"
val scala3Version = "3.4.0"

lazy val root = project
  .in(file("."))
  .settings(
    name := "jvm-languages-tour-sbt-sc",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "1.0.0" % Test
  )

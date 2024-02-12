ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.3"

lazy val root = (project in file("."))
  .settings(
    name := "zero-deep-learning",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest"     % "3.2.15" % Test,
      "io.circe"      %% "circe-core"    % "0.14.1",
      "io.circe"      %% "circe-generic" % "0.14.1",
      "io.circe"      %% "circe-parser"  % "0.14.1"
    )
  )

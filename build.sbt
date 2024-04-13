ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.3"

resolvers += "GitHub Package Registry" at "https://maven.pkg.github.com/rinotc/smatrix"
credentials += Credentials(
  "GitHub Package Registry",
  "maven.pkg.github.com",
  "rinotc",
  sys.env.getOrElse("GITHUB_TOKEN", "N/A")
)

lazy val root = (project in file("."))
  .settings(
    name := "zero-deep-learning",
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest"     % "3.2.18" % Test,
      "smatrix"       %% "smatrix"       % "0.0.5",
      "io.circe"      %% "circe-core"    % "0.14.6",
      "io.circe"      %% "circe-generic" % "0.14.6",
      "io.circe"      %% "circe-parser"  % "0.14.6"
    )
  )

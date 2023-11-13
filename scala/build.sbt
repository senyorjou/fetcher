

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .enablePlugins(JavaAppPackaging)
  .settings(
    name := "scala",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-stream" % "2.8.5",
      "com.typesafe.akka" %% "akka-http" % "10.5.3",
      "com.typesafe.akka" %% "akka-http-spray-json" % "10.5.3",
      "io.spray" %% "spray-json" % "1.3.6"
    ),
    dockerBaseImage := "adoptopenjdk/openjdk11",
    dockerExposedPorts ++= Seq(9000, 9001),
    dockerExposedUdpPorts += 4444,
    dockerAlias := dockerAlias.value.withName("fetcher-scala").withTag(Some("1.0.0"))

  )

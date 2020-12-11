name := "sangria-akka-streams"
organization := "org.sangria-graphql"
mimaPreviousArtifacts := Set("org.sangria-graphql" %% "sangria-akka-streams" % "1.0.1")

description := "Sangria akka-streams integration"
homepage := Some(url("http://sangria-graphql.org"))
licenses := Seq("Apache License, ASL Version 2.0" → url("http://www.apache.org/licenses/LICENSE-2.0"))

scalaVersion := "2.13.0"
crossScalaVersions := Seq("2.11.12", "2.12.10", scalaVersion.value)

scalacOptions ++= Seq("-deprecation", "-feature")

scalacOptions ++= {
  if (scalaVersion.value startsWith "2.11")
    Seq("-target:jvm-1.7")
  else
    Seq("-target:jvm-1.8")
}
javacOptions ++= {
  if (scalaVersion.value startsWith "2.11")
    Seq("-source", "7", "-target", "7")
  else
    Seq("-source", "8", "-target", "8")
}

libraryDependencies ++= Seq(
  "org.sangria-graphql" %% "sangria-streaming-api" % "1.0.1",
  "com.typesafe.akka" %% "akka-stream" % "2.5.32",
  "org.scalatest" %% "scalatest" % "3.2.3" % Test)

// Publishing

releaseCrossBuild := true
releasePublishArtifactsAction := PgpKeys.publishSigned.value
publishMavenStyle := true
publishArtifact in Test := false
pomIncludeRepository := (_ => false)
publishTo := Some(
  if (version.value.trim.endsWith("SNAPSHOT"))
    "snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  else
    "releases" at "https://oss.sonatype.org/service/local/staging/deploy/maven2")

startYear := Some(2016)
organizationHomepage := Some(url("https://github.com/sangria-graphql"))
developers := Developer("OlegIlyenko", "Oleg Ilyenko", "", url("https://github.com/OlegIlyenko")) :: Nil
scmInfo := Some(ScmInfo(
  browseUrl = url("https://github.com/sangria-graphql/sangria-akka-streams.git"),
  connection = "scm:git:git@github.com:sangria-graphql/sangria-akka-streams.git"
))

// nice *magenta* prompt!

shellPrompt in ThisBuild := { state =>
  scala.Console.MAGENTA + Project.extract(state).currentRef.project + "> " + scala.Console.RESET
}

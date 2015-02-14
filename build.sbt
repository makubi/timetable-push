import sbt._

name := """UntisCrawler"""

version := "1.0-SNAPSHOT"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "net.debasishg" %% "redisclient" % "2.14",
  "com.github.nscala-time" %% "nscala-time" % "1.8.0",
  "org.scala-lang.modules" %% "scala-pickling" % "0.10.0",
  "org.mindrot" % "jbcrypt" % "0.3m"
)

import sbt._

name := """UntisCrawler"""

version := "1.0-SNAPSHOT"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb)

scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  ws,
  "org.scaldi"  %%  "scaldi-play"   % "0.5.3",
  "org.scaldi"  %%  "scaldi-akka"   % "0.5.3",
  "org.mongodb" %%  "casbah"        % "2.8.0",
  "joda-time"   %   "joda-time"     % "2.7",
  "org.joda"    %   "joda-convert"  % "1.7",
  "com.novus"   %%  "salat"         % "1.9.9",
  "org.mindrot" %   "jbcrypt"       % "0.3m",
  "com.fasterxml.jackson.module"    % "jackson-module-scala_2.11"   % "2.5.1"
)

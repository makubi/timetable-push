import sbt._
import NativePackagerKeys._

name := """timetable-push"""

version := "1.0-SNAPSHOT"

resolvers += "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"

lazy val root = (project in file(".")).enablePlugins(PlayScala, SbtWeb)
  
scalaVersion := "2.11.4"

libraryDependencies ++= Seq(
  ws,
  "org.scaldi"                    %%  "scaldi-play"   % "0.5.3",
  "org.scaldi"                    %%  "scaldi-akka"   % "0.5.3",
  "com.typesafe.play"             %%  "play-slick"    % "0.8.1",
  "postgresql"                    %   "postgresql"    % "9.1-901.jdbc4",
  "joda-time"                     %   "joda-time"     % "2.7",
  "org.joda"                      % "joda-convert"    % "1.6",
  "com.github.tototoshi"          %% "slick-joda-mapper" % "1.2.0",
  "org.joda"                      %   "joda-convert"  % "1.7",
  "org.mindrot"                   %   "jbcrypt"       % "0.3m",
  "com.fasterxml.jackson.module"  %   "jackson-module-scala_2.11"   % "2.5.1"
)

// setting a maintainer which is used for all packaging types</pre>
maintainer:= "Mathias Kub"
 
// exposing the play ports
dockerExposedPorts in Docker := Seq(9000, 9443)
 
// run this with: docker run -p 9000:9000 <name>:<version>

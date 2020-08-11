
/*
 * BASIC INFORMATION
 ********************************************************/


name := "ubirch-json-utils"
version := "0.5.2"
description := "collection of JSON utils"
organization := "com.ubirch.util"
homepage := Some(url("http://ubirch.com"))
scalaVersion := "2.11.12"
scalacOptions ++= Seq(
  "-feature"
)
scmInfo := Some(ScmInfo(
  url("https://github.com/ubirch/ubirch-json-utils"),
  "https://github.com/ubirch/ubirch-json-utils.git"
))

/*
 * CREDENTIALS
 ********************************************************/

(sys.env.get("CLOUDREPO_USER"), sys.env.get("CLOUDREPO_PW")) match {
  case (Some(username), Some(password)) =>
    println("USERNAME and/or PASSWORD found.")
    credentials += Credentials("ubirch.mycloudrepo.io", "ubirch.mycloudrepo.io", username, password)
  case _ =>
    println("USERNAME and/or PASSWORD is taken from /.sbt/.credentials")
    credentials += Credentials(Path.userHome / ".sbt" / ".credentials")
}


/*
 * RESOLVER
 ********************************************************/

val resolverSeebergerJson = Resolver.bintrayRepo("hseeberger", "maven")

resolvers ++= Seq(
  Resolver.sonatypeRepo("releases"),
  Resolver.sonatypeRepo("snapshots"),
  resolverSeebergerJson)


/*
 * PUBLISHING
 ********************************************************/

val resolverUbirchUtils = "cloudrepo.io" at "https://ubirch.mycloudrepo.io/repositories/ubirch-utils-mvn"

publishTo := Some(resolverUbirchUtils)
publishMavenStyle := true


/*
 * DEPENDENCIES
 ********************************************************/

//version
val json4sV = "3.6.0"

//dependencies
val seebergerJson4s = "de.heikoseeberger" %% "akka-http-json4s" % "1.21.0"
val scalaTest = "org.scalatest" %% "scalatest" % "3.0.5"
val jodaTime = "joda-time" % "joda-time" % "2.10"

val json4sJackson = "org.json4s" %% "json4s-jackson" % json4sV
val json4sCore = "org.json4s" %% "json4s-core" % json4sV
val json4sExt = "org.json4s" %% "json4s-ext" % json4sV
val json4sNative = "org.json4s" %% "json4s-native" % json4sV

val json4sBase = Seq(
  json4sCore,
  json4sJackson,
  json4sExt
)
val json4sWithNative = json4sBase :+ json4sNative

libraryDependencies ++= Seq(
  seebergerJson4s,
  scalaTest % "test",
  jodaTime % "test"
) ++ json4sWithNative



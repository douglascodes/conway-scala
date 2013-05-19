import sbt._
import sbt.Keys._

object Build extends Build {

  lazy val conway = Project(
    "conway_GOL_simulator",
    file("."),
    settings = 
      Defaults.defaultSettings ++
      Seq(
        organization := "com.DouglasCodes",
        version := "1.0.0",
        scalaVersion := "2.9.2",
        scalacOptions ++= Seq("-unchecked", "-deprecation"),
        libraryDependencies ++= Seq(
          "org.specs2" %% "specs2" % "1.12.3" % "test"
        )
        ,
        retrieveManaged := true,
        initialCommands in console := "import com.DouglasCodes.Conway._"
      )
  )
}

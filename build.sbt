Global / onChangedBuildSource := ReloadOnSourceChanges

inThisBuild(
  List(
    organization := "org.soundsofscala",
    scalaVersion := "3.3.3",
    semanticdbEnabled := true,
    semanticdbVersion := scalafixSemanticdb.revision,
    scalafixDependencies ++= List(
      "com.github.xuwei-k" %% "scalafix-rules" % "0.3.0"
    ),
    resolvers +=
      "Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
  ))

lazy val root = project
  .in(file("."))
  .aggregate(sos.js, sos.jvm)
  .settings(
    publish := {},
    publishLocal := {}
  )

lazy val sos = crossProject(JSPlatform, JVMPlatform)
  .in(file("."))
  .settings(
    name := "sounds-of-scala",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      "org.scalactic" %%% "scalactic" % "3.2.17",
      "org.scalatest" %%% "scalatest" % "3.2.17" % Test,
      "org.typelevel" %%% "cats-core" % "2.10.0",
      "org.typelevel" %%% "cats-effect" % "3.5.2",
      "io.kevinlee" %%% "refined4s-core" % "0.11.0",
      "io.kevinlee" %%% "refined4s-cats" % "0.11.0",
      "io.kevinlee" %%% "refined4s-pureconfig" % "0.11.0"
    )
  )
  .jvmSettings(
    libraryDependencies += "org.scala-js" %% "scalajs-stubs" % "1.1.0" % "provided"
  )
  .jsSettings(
    scalaJSUseMainModuleInitializer := true,
    scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) },
    Compile / mainClass := Some("org.soundsofscala.Main"),
    Test / requireJsDomEnv := true,
    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv(),
    libraryDependencies ++= Seq("org.scala-js" %%% "scalajs-dom" % "2.8.0"),
    npmExtraArgs ++= Seq(
      "--registry=https://registry.npmjs.org/"
    )
  )
  .jsConfigure(project => project.enablePlugins(ScalaJSBundlerPlugin))

import sbtwelcome.*

// Generated from http://patorjk.com/software/taag/#p=display&f=Stronger%20Than%20All&t=Sounds%20of%20Scala
logo :=
  raw"""
       |.________._______  .____     .______  .______  .________     ._______  ._______     .________._______ .______  .___    .______
       ||    ___/: .___  \ |    |___ :      \ :_ _   \ |    ___/     : .___  \ :_ ____/     |    ___/:_.  ___\:      \ |   |   :      \
       ||___    \| :   |  ||    |   ||       ||   |   ||___    \     | :   |  ||   _/       |___    \|  : |/\ |   .   ||   |   |   .   |
       ||       /|     :  ||    :   ||   |   || . |   ||       /     |     :  ||   |        |       /|    /  \|   :   ||   |/\ |   :   |
       ||__:___/  \_. ___/ |        ||___|   ||. ____/ |__:___/       \_. ___/ |_. |        |__:___/ |. _____/|___|   ||   /  \|___|   |
       |   :        :/     |. _____/     |___| :/         :             :/       :/            :      :/          |___||______/    |___|
       |            :       :/                 :                        :        :                    :
       |                    :
       |${scala.Console.YELLOW}Scala ${scalaVersion.value}${scala.Console.RESET}
       |
       |""".stripMargin

usefulTasks := Seq(
  UsefulTask("~fastOptJS / webpack", "Run fastOptJS for live updates").alias("f"),
  UsefulTask("reload", "run reload").alias("r"),
  UsefulTask("clean", "run clean").alias("cln"),
  UsefulTask("compile", "run compile").alias("c"),
  UsefulTask("test", "Run test").alias("t"),
  UsefulTask("scalafmtAll", "Run scalafmtAll on the entire project").alias("fmt"),
  UsefulTask(
    "scalafmtCheckAll",
    "Run scalafmtCheckAll on the entire project"
  ).alias("fmtchk"),
  UsefulTask("scalafixAll", "Run scalafixAll on the entire project").alias("fix"),
  UsefulTask(
    "scalafixAll --check",
    "Run scalafixAll --check on the entire project"
  ).alias("fixchk"),
  UsefulTask("fmtchk; fixchk", "Run fmtchk; fixchk").alias("chk"),
  UsefulTask(
    "c; t; chk",
    "Build - Run compile; test; scalafmtCheckAll; scalafixAll --check"
  ).alias("bld"),
  UsefulTask(
    "cln; c; t; chk",
    "Clean Build - Run compile; test; scalafmtCheckAll; scalafixAll --check"
  ).alias("cbld"),
  UsefulTask("dependencyUpdates", "Run dependencyUpdates").alias("du")
)

logoColor := scala.Console.YELLOW
aliasColor := scala.Console.BLUE
commandColor := scala.Console.CYAN
descriptionColor := scala.Console.WHITE

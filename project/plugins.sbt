logLevel := sbt.Level.Debug

resolvers += "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/"

//The play plugin
addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.5.9")

addSbtPlugin("com.typesafe.sbt" % "sbt-play-ebean" % "4.0.1")
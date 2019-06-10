name := "westminsterlibrary"
 
version := "1.0" 
      
lazy val `westminsterlibrary` = (project in file(".")).enablePlugins(PlayJava,PlayEbean)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
scalaVersion := "2.11.11"

libraryDependencies += jdbc
libraryDependencies += guice
libraryDependencies ++= Seq( javaJdbc , cache , javaWs )
libraryDependencies += javaForms
libraryDependencies += ws
libraryDependencies += ehcache

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.41"

      
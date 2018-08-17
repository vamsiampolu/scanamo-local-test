name := "scanamo-test-example"

version := "0.1"

scalaVersion := "2.12.6"

libraryDependencies += "com.gu" %% "scanamo" % "1.0.0-M6"
libraryDependencies += "org.scalatest" %% "scalatest" % "3.0.5" % Test
libraryDependencies += "com.gu" %% "scanamo" % "1.0.0-M6" % Test
libraryDependencies += "com.gu" %% "scanamo-testkit" % "1.0.0-M7" % Test

dynamoDBLocalPort := 8042
startDynamoDBLocal := startDynamoDBLocal.dependsOn(compile in Test).value
test in Test := (test in Test).dependsOn(startDynamoDBLocal).value
testOnly in Test := (testOnly in Test).dependsOn(startDynamoDBLocal).evaluated
testOptions in Test += dynamoDBLocalTestCleanup.value
# SFDC- QADEV07

This is a project that lets **QADEV07** team practices refactor code of a project that has developed by another team.

This project is an Automated framework that will automate acceptance and smoke test for
[SalesForce](https://www.salesforce.com).

##Project Running
To run this framework it is required the following:
   1. Intellij idea IDE
   2. Gradle 2.14

 Once  those requirements are done follow these steps:
   1. Open the project with the intellij ide.
   2. Open the gradle properties file e.g. gradle.properties
      [gradle.properties file](images/gradleProperties.jpg)
   3. Set the required parameter:
        * primaryUserName :  is the user account name
        * primaryUserPassword : Password of the user
        * primaryUsePasswordToken :password and token together
        * proxyHost
        * proxyPort
        * displayName : user name displayed on the mainboard
   4. Then run the TestNGSuite.xml file: **src>test>resources>suites>TestNGSuite.xml**
   5. Then run the following command on comand promp: ***gradle executeFeatures –Pemail=$email –Ppassword=$password –PapiToken=$apiToken -PcucumberOptions="--tags @smoke"***
   
 After executing those steps the frame should be executed.

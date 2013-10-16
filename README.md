fishball
========

Application Source Code & Checking runable

1. download playframework JDK 7
http://downloads.typesafe.com/play/1.2.7/play-1.2.7.zip

2. config the PATH variable

3. create database fishball

4. run the fishball.sql to setup the database fishball

5. use git to checkout the project https://github.com/superleo-cn/fishball.git

6. use the command line to download plugins for this project
>cd fishball
>play install ebean 1.0.6
>play dependencies
>play ec (remember to run this command once you include a new plugin)

6. use the command line to run the application
>cd fishball
>play run

7. If it started successfully, you may access http://localhost:8080
username / password: 1 / 1

Eclipse setup

1. use the command line to setup the eclipse project
>cd fishball
>play ec

2. Once you finish the step, you can use eclipse to import the project

3. please DO NOT check in the generated folders. Different user likes different IDE, so just keep the project as clean as we can. Currently the nessesary folders are:
>app  (All the Java source code) <br />
>test (Junit test case) <br />
>conf (properties files, eg, db connection, url path) <br />
>lib  (3rd parties dependent liberaries) <br />
>public (All the javascript, css and images)

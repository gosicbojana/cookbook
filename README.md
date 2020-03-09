# Cookbook

Copyright © 2020 Bojana Gosic 2019/3707

# Project description
Cookbook project is written in Clojure as student's assignment for university subject. Cookbook is an API for managing recipes, using Swagger. 
Using this application you will be able to manage recipes for different food categories. Apart from that you have possibility to work with different users.

# What do you need to run project?

You will need to instal Java, Java 1.8.0 is preffered version along with MySql. For installing Leiningen please refer to their's 
official documentation https://leiningen.org/. When you install all above stated, you will be able to run lein -v and lein repl.
If you use Visual Studio Code as your IDE, please install Closure plugin for smoothly running of application. 

# How to run project?
Using terminal run lein ring server to start project. After running this command in application will be started in your default browser. 
Before running application please change username and password for database in connection.clj. Database folder has provided .sql file for seeding you database.

# Architecture of project
Project is separated in following sections. 
In domain we have all domain classes such as recipe and category. 
In database folder we store settings to connect to the database.
Queries folders holds all queries to the database.
Swagger folder is holding actions for out Swagger API and uses queries to retrieve data from database.

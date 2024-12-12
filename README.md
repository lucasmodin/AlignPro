# AlignPro
A Project management tool

## Table of Contents
- [About the Project](#about-the-project)
- [Features](#features)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)

## About the Project
AlignPro is a project management time system, where one can have a overview of they projects, subproject, task and subtask. The program lets you create, edit and remove these items. It solzes the problem of needing a good overview for handling projects and what the future needs.

### Built With
- Java 21
- Maven
- Spring boot
- thymeleaf
- Jdbc
- mysql
- h2
- Azure
- Git/Github-actions

## Features
- Create: Can create projects, subprojects, task and subprojects, Employees
- Edit: Can edit projects, subprojects, task and subprojects, Employees
- Delete:  Can delete projects, subprojects, task and subprojects
- Dashboard: Overview of dashboard
- Login: A credentials validation
- Logout: A session ending 

## Installation
Step-by-step instructions to install and set up the project locally:
1. Clone the repo:
   - git clone https://github.com/lucasmodin/AlignPro.git 
2. Install dependencies:
   - Re-run pom dependencies if failed
3. Edit run-configuration
   - Set the environment variables
   (PROD_DB_USER : alignproadmin)
   (PROD_DB_URL : jdbc:mysql://alignproserver.mysql.database.azure.com:3306/alignprodb?useSSL=true)
   (PROD_DB_PASSWORD : Detremusketerer2024)
4. Connect to MySQL database via mysql workbench. Same properties as in point 3. For prod control
5. Start the application:
   -  For localhost. Ignore point 3 and 4.
   - Look for populated data from Pm_User to login via a user
   - Enjoy

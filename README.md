# Single Page Application using SpringBoot & AngularJS 1.2
A complete Web application developed using Spring Boot and AngularJS to demonstrate the features of AngularJS 1.x

In this, you will see the following in action.

- Single Page Application using Angular Route
- Login using Spring Security
- Simple search using Spring JPA
- CRUD using Spring JPA

Note: The application development has just started with initial features. Watch out this space for more updates!


## Prerequisites
The following items should be installed in your system:

- MySQL Database
- GIT

## Database Configuration
The following items should be setup in MySQL Database.

* Create the database named 'spring_app'
* Configure the database host, port, username and password in the 'application.properties' file located in 'src/main/resources/' directory
* Database schema queries

CREATE TABLE `employee` (
  `EMP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REMARKS` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EMP_ID`),
  UNIQUE KEY `EMP_ID_UNIQUE` (`EMP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1018 DEFAULT CHARSET=latin1;

## Running application locally
- In the command line, execute the command : git clone https://github.com/JavaTechExplorers/SPA-SpringBoot-AngularJS.git
- cd SPA-SpringBoot-AngularJS
- gradlew build && java -jar build\libs\vicky-spring-app-0.1.0.jar


## Technologies Used
- HTML-5
- Bootstrap theme
- AngularJS 1.2
- jQuery
- Spring Boot

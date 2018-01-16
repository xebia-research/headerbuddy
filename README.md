# HeaderBuddy
[![Build Status](https://travis-ci.org/xebia-research/headerbuddy.svg?branch=release%2F1.0.0)](https://travis-ci.org/xebia-research/headerbuddy)

HeaderBuddy is an API commissioned by Xebia and developed by students of the [Windesheim University Of Applied Sciences](https://www.windesheim.nl/). The Headerbuddy API checks the configured HTTP headers of a webserver and returns an advice report based on the returned HTTP headers. 

## Prerequisites
The following should be installed:
* Docker
* Maven 
* Java 8 or higher

## Installing
### 1. Clone the project
```
git clone https://github.com/xebia-research/headerbuddy.git
```
### 2. Create the java package
```
mvn package -DskipTests=true
```
Note: Make sure you are in the project directory.
### 3. Run the mysql database container
```
docker-compose up -d db
```
### 4. Run the HeaderBuddy application container (API key required)
```
docker-compose up -d headerbuddy
```
### 4. Run the HeaderBuddy application container (API key not required)
```
docker-compose run -d --entrypoint "java -jar headerbuddy-[version].jar --key.required = fals
e" -p 8080:8080 headerbuddy
```
Note: Give the database container enough time (approximately 15 seconds) to start up before running the application container.

Note: If the port is unavailable, use `docker-compose run -p` and after the -p add a custom port number. Example: `-p 1234:8080`. The application now runs on "localhost:1234". **Important:** Keep the port number on the right of the ":" the same as configured in the application.properties file. 
## Running the tests
```
mvn verify
```
Note: Make sure the database container is running and the application container is ***not*** running.

## License
Xebia HeaderBuddy is open-sourced licensed under the [MIT license](http://opensource.org/licenses/MIT).

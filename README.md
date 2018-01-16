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
### 4. Run the HeaderBuddy application container
```
docker-compose up -d headerbuddy
```
Note: Give the database container enough time (approximately 15 seconds) to start up before running the application container.
### (Optional) Run the HeaderBuddy application container without API key
```
docker-compose run -d --entrypoint "java -jar headerbuddy-[version].jar --key.required = fals
e" -p 8080:8080 headerbuddy
```
## Running the tests
```
mvn verify
```
Note: Make sure the database container is running and the application container is ***not*** running.

## License
Xebia HeaderBuddy is open-sourced licensed under the [MIT license](http://opensource.org/licenses/MIT).

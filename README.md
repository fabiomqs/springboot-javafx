# springboot-javafx
Projects studying and practicing with javafx

## Project 1:
- **stock-service**
- Kotlin api 
- reactive Spring
- Just return a random price every secondo
- produces a stream 

## Project 2:
- **stock-client**
- Java Client to consume stock-service

## Project 3:
- **stock-ui**
- JavaFX front-end for the stock prices application
- Added the client of Project 2
- uses lombok
- load the fxml as event oriented
### Steps:
- create a new springboot app wihtout adding dependencies
- add the javaFX dependency:
```
<dependency>
	<groupId>org.openjfx</groupId>
	<artifactId>javafx-graphics</artifactId>
	<version>18</version>
</dependency>

<dependency>
    <groupId>org.openjfx</groupId>
    <artifactId>javafx-fxml</artifactId>
    <version>18</version>
</dependency>
```

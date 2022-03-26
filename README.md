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
# Samples
## Sample 1:
- **sample1**
- Stage
- maven project
-Issue #1

### pom:

```
<properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    <javafx.version>17.0.1</javafx.version>
    <javafx.maven.plugin.version>0.0.8</javafx.maven.plugin.version>
  </properties>

  <dependencies>
    <dependency>
      <groupId>org.openjfx</groupId>
      <artifactId>javafx-controls</artifactId>
      <version>${javafx.version}</version>
    </dependency>
  </dependencies>
  
  <build>
    <plugins>
      <plugin>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-maven-plugin</artifactId>
        <version>${javafx.maven.plugin.version}</version>
        <configuration>
          <mainClass>HelloFX</mainClass>
        </configuration>
      </plugin>
    </plugins>
  </build>
```
- Note that transitive dependencies are automatically resolved (for instance, there is no need to add a dependency for the javafx.graphics module, since it is transitively resolved by the javafx.controls module). But if your application is using FXML, you will need to add a dependency for the javafx.fxml module as well.

- mvn clean javafx:run
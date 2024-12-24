# DefenseOS

## Sample Stdout

I saved a sample of the applications stdout [here.](samplestdout.txt)

## Prerequisites

- Java 21

## Running

### Using Gradle

```
.\gradlew.bat :defenseos-sawtooth:run
or ./gradlew :defenseos-sawtooth:run
```

### Using UberJar

```
.\gradlew.bat uberJar
or ./gradlew uberJar

java -jar defenseos-sawtooth\build\libs\defenseos-sawtooth-1.0-SNAPSHOT-uber.jar
```

## Generating Javadocs

```
.\gradlew.bat javadoc
```

The generated Javadocs can be found for each module here: `**/build/docs/javadoc/index.html`.

## Testing

Run the following to execute tests:

```
.\gradlew.bat test
or ./gradlew test
```

Code coverage reports can be found for each module that has tests here: `**/build/reports/jacoco/test/html/index.html`.
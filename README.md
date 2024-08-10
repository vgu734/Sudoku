# Sudoku
Exploration into java springboot with Sudoku. This uses build uses gradle.

## Versioning
Java: openjdk 17.0.12
Gradle: 8.9

## Build
Leverage gradle to both build the source jar file and the docker image that will run the application.
```
gradle clean dockerBuild
```

## Deploy
Deploys with exposed port localhost:8080
```
./deploy.sh
```

## Delete
Stops and removes the container
```
./delete.sh
```

## Clean
Clean up docker
```
./clean.sh
```
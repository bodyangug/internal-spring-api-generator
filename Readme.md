## General info

That project was created to simulate an enormous-size of project.

## Workflow

Prerequisite:

* check that folder ```output``` was created at the root of the project;
* check that package ```web.controller``` was created at the root of ```com.epam.drill4j``` package.

1. In the main method uncomment lines from 12 to 19
2. Provide necessary numbers to params.
3. Run using ``main`` method
4. Wait until app will have generated all classes
5. Open terminal and type:

```
mvn clean install
```

6. Build docker image:

```
 docker build . --no-cache -t drill4j/generated-mock-api:0.1.0
```

7. As an example was created docker-compose.yml file. Here you can find how you can use created image

### Additional

Also, you can use main method and **runApplication<App>(*args)*** to run spring locally, but
that method would take too much time, If app had generated huge batch of classes.

## Output folder

You can check output folder after generate classes job have done. Here you can find metadata to all created controllers.
That metadata contains next:

* type: type of HTTP method
* url: url to that controller
* numberOfParams: count of params

That file you should use in the  [internal_spring_api_requester](https://github.com/bodyangug/internal_spring_api_requester) api

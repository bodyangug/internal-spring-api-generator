package com.epam.internal.spring

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Configuration

@SpringBootApplication(scanBasePackages = ["com.epam.internal.spring.web"])
@Configuration
class App

fun main(args: Array<String>) {
    //uncomment if you need to create some classes
    //NOTE: do not forget to comment 11 line
//    ClassCreator().create(
//        numberOfPackages = 10,
//        numberOfClasses = 1250,
//        numberOfMethods = 25,
//        numberOfBranches = 3
//    )
    runApplication<App>(*args)
}

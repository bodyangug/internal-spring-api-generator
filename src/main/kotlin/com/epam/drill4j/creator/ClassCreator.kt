package com.epam.drill4j.creator

import com.epam.drill4j.metadata.MetadataGenerator
import com.epam.drill4j.metadata.model.MetadataModel
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.nio.file.Files
import java.util.*
import kotlin.io.path.Path

class ClassCreator(
    private val packagePath: String = "src/main/kotlin/com/epam/drill4j/web/controller/",
    private val metaGenerator: MetadataGenerator = MetadataGenerator()
) {

    /**
     * Create method requires to generate in pointed package classes with controllers
     *
     * @param numberOfPackages number of packages that will be created
     * @param numberOfClasses number of classes that will be created
     * @param numberOfMethods number of methods in class that will be created
     * @param numberOfBranches number of if/else blocks that will be created in each method
     */
    fun create(
        numberOfPackages: Int,
        numberOfClasses: Int,
        numberOfMethods: Int,
        numberOfBranches: Int
    ) {
        val numberOfClassesPerPackage: Int = numberOfClasses / numberOfPackages
        var counterOfClasses = 0
        for (packageNum in 0 until numberOfPackages) {
            var counter = 0
            val packageName = UUID.randomUUID()
            for (i in 0 until numberOfClasses) {
                val className = "TestController$counterOfClasses"
                if (counter == numberOfClassesPerPackage) {
                    continue
                }

                val fullPackage = "$packagePath$packageName"
                if (Files.notExists(Path(fullPackage))) {
                    Files.createDirectory(Path(fullPackage))
                }
                val fileName = "$fullPackage/$className.kt"
                File(fileName).createNewFile()
                BufferedWriter(FileWriter(fileName)).use { writer ->
                    //write package
                    writer.write("package com.epam.drill4j.web.controller;\n")
                    //write imports
                    writer.write(
                        """
                    import org.springframework.http.HttpStatus
                    import org.springframework.http.ResponseEntity
                    import org.springframework.stereotype.Controller
                    import org.springframework.web.bind.annotation.GetMapping
                    import org.springframework.web.bind.annotation.RequestMapping
                    import org.springframework.web.bind.annotation.RequestParam
                    import org.springframework.web.bind.annotation.ResponseBody
                    """.trimIndent()
                    )
                    //write class name
                    writer.write(
                        """
                        @Controller
                        @RequestMapping("/${counterOfClasses}")
                        class $className {
                    """.trimIndent()
                    )
                    for (j in 0 until numberOfMethods) {
                        val pathToMapping = "/pets/$j"
                        val methodName = String.format("method%s", j)
                        val paramString = getMethodsParam(numberOfBranches)
                        val methodContent: String = getMethodContent(numberOfBranches)
                        writer.write(
                            """
                            @GetMapping("$pathToMapping")
                            @ResponseBody
                            fun $methodName($paramString) : ResponseEntity<HttpStatus> {
                                $methodContent
                        """.trimIndent()
                        )
                        writer.write("\nreturn ResponseEntity<HttpStatus>(HttpStatus.OK)")
                        writer.write("\n    }\n")
                        metaGenerator.add(
                            MetadataModel(
                                method = "GET",
                                url = "$counterOfClasses$pathToMapping",
                                numberOfParams = 3
                            )
                        )
                    }
                    //end of the class
                    writer.write("}")
                    counter++
                }
                counterOfClasses++
            }

        }
        metaGenerator.generate()
    }

    /**
     * Get method content returns if else blocks by numberOfBranches number
     *
     * @param numberOfBranches number of if/else blocks that will be created in each method
     * @return Content for method in a String format
     */
    private fun getMethodContent(numberOfBranches: Int): String {
        var result = ""
        for (i in 0 until numberOfBranches) {
            result += if (i == 0) {
                """
                            if (param$i) {
                                println("param$i")
                            } 
                        """.trimIndent()
            } else {
                """
                            else if (param$i) {
                                println("param$i")
                            }
                        """.trimIndent()
            }
        }
        return result
    }

    /**
     * Get methods param
     *
     * @param numberOfBranches number of if/else blocks that will be created in each method
     * @return arguments to controller method
     */
    private fun getMethodsParam(numberOfBranches: Int): String {
        var result = ""
        for (i in 0 until numberOfBranches) {
            result += if (numberOfBranches - 1 == i) {
                "@RequestParam param$i: Boolean"
            } else {
                "@RequestParam param$i: Boolean,"
            }
        }
        return result
    }
}

package com.epam.internal.spring.metadata

import com.epam.internal.spring.metadata.model.MetadataModel
import com.google.gson.Gson
import java.io.BufferedWriter
import java.io.FileWriter

class MetadataGenerator(
    private val list: ArrayList<MetadataModel> = ArrayList(),
    private val filePath: String = "output/endpoints.json"
) {

    /**
     * Generate method is using to create file in "output" folder with metadata from inner list of Models
     */
    fun generate() {
        val gson = Gson()
        val json = gson.toJson(list)

        BufferedWriter(FileWriter(filePath)).use { writer ->
            writer.write(json)
        }
    }

    /**
     * Before generating process that method should fill the list using model
     *
     * @param model MetadataModel
     */
    fun add(model: MetadataModel) {
        list.add(model)
    }

}

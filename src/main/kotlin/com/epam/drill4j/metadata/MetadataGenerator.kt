package com.epam.drill4j.metadata

import com.epam.drill4j.metadata.model.MetadataModel
import com.google.gson.Gson
import java.io.BufferedWriter
import java.io.FileWriter

class MetadataGenerator(
    private val list: ArrayList<MetadataModel> = ArrayList(),
    private val filePath: String = "output/endpoints.json"
) {

    fun generate() {
        val gson = Gson()
        val json = gson.toJson(list)

        BufferedWriter(FileWriter(filePath)).use { writer ->
            writer.write(json)
        }
    }

    fun add(model: MetadataModel) {
        list.add(model)
    }

}

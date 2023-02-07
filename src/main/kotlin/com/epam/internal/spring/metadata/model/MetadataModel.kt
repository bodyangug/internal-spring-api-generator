package com.epam.internal.spring.metadata.model

/**
 * Metadata model
 *
 * @property method HTTP method
 * @property url URL to created controller
 * @property numberOfParams number of params for controller
 */
data class MetadataModel(
    val method: String,
    val url: String,
    val numberOfParams: Int
)

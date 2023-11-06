package com.bb.elasticsearch.controller

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.elasticsearch.indices.CreateIndexResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
class IndicesController(
    val esClient: ElasticsearchClient
) {

    @PostMapping("/indices")
    fun createIndices(@RequestBody indicesRequest: CreateIndicesRequest): ResponseEntity<CreateIndexResponse> {
        val response = esClient.indices()
            .create { c -> c.index(indicesRequest.name) }
        return ResponseEntity.ok(response)
    }

}
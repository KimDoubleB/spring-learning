package com.bb.elasticsearch.model

import org.springframework.data.elasticsearch.annotations.Document
import java.time.LocalDateTime

@Document(indexName = "test")
data class Blog(val id: Long,
                val title: String,
                val content: String,
                val createdAt: LocalDateTime,
                val modifiedAt: LocalDateTime,
                val deletedAt: LocalDateTime?)

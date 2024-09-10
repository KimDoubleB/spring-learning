package com.bb.elasticsearch.controller

import com.bb.elasticsearch.model.Blog
import java.time.LocalDateTime
import java.time.ZoneOffset

data class CreateIndicesRequest(val name: String)

data class CreateBlogRequest(val title: String, val content: String) {
    fun toBlog(): Blog {
        val now = LocalDateTime.now()
        return Blog(id = now.toEpochSecond(ZoneOffset.UTC),
                title = this.title,
                content = this.content,
                createdAt = now,
                modifiedAt = now,
                deletedAt = null)
    }

}

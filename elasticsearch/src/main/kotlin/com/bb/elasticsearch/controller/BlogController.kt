package com.bb.elasticsearch.controller

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.elasticsearch.core.GetRequest
import co.elastic.clients.elasticsearch.core.SearchRequest
import co.elastic.clients.elasticsearch.core.search.Hit
import com.bb.elasticsearch.model.Blog
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/blog")
class BlogController(
        val esClient: ElasticsearchClient
) {

    @PostMapping
    fun createBlog(@RequestBody blogRequest: CreateBlogRequest): ResponseEntity<Unit> {
        val blog = blogRequest.toBlog()
        val index = esClient.index { i ->
            i.index("test")
                    .id(blog.id.toString())
                    .document(blog)
        }
        println("index = $index")
        return ResponseEntity.ok().build()
    }

    @GetMapping("/{id}")
    fun getBlog(@PathVariable id: String): ResponseEntity<Blog> {
        val result = esClient.get(
                GetRequest.of { g -> g.index("test").id(id) },
                Blog::class.java
        )
        println("result = $result")
        return ResponseEntity.ok(result.source())
    }


    @GetMapping("/search")
    fun searchBlog(@RequestParam title: String): ResponseEntity<List<Hit<Blog>>> {
        val request = SearchRequest.of { s ->
            s.index("test")
                    .query { q ->
                        q.match { t ->
                            t.field("title").query(title)
                        }
                    }
        }
        val result = esClient.search(request, Blog::class.java)
        println("result = $result")
        println("result = ${result.hits()}")
        println("result = ${result.hits().hits()}")
        return ResponseEntity.ok(result.hits().hits())
    }

}

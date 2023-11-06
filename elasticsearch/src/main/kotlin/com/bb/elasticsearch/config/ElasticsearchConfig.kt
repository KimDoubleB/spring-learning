package com.bb.elasticsearch.config

import co.elastic.clients.elasticsearch.ElasticsearchClient
import co.elastic.clients.json.jackson.JacksonJsonpMapper
import co.elastic.clients.transport.rest_client.RestClientTransport
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.http.Header
import org.apache.http.HttpHeaders
import org.apache.http.HttpHost
import org.apache.http.message.BasicHeader
import org.elasticsearch.client.RestClient
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(ElasticsearchProperty::class)
class ElasticsearchConfig {

    @Bean
    fun restClient(elasticsearchProperty: ElasticsearchProperty): RestClient {
        return RestClient.builder(HttpHost.create(elasticsearchProperty.serverUrl))
            .setDefaultHeaders(getElasticsearchHeaders(elasticsearchProperty.apiKey))
            .build()
    }

    @Bean
    fun restClientTransport(
        restClient: RestClient,
        objectMapper: ObjectMapper
    ): RestClientTransport {
        val jacksonJsonpMapper = JacksonJsonpMapper(objectMapper)
        return RestClientTransport(restClient, jacksonJsonpMapper)
    }

    @Bean
    fun elasticsearchClient(restClientTransport: RestClientTransport): ElasticsearchClient {
        return ElasticsearchClient(restClientTransport)
    }

    private fun getElasticsearchHeaders(apiKey: String?): Array<Header> {
        if (apiKey != null) {
            return arrayOf(
                BasicHeader(
                    HttpHeaders.AUTHORIZATION,
                    "ApiKey $apiKey"
                )
            )
        }
        return emptyArray<Header>()
    }

}

@ConfigurationProperties(prefix = "elasticsearch")
data class ElasticsearchProperty(val serverUrl: String, val apiKey: String?)

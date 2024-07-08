package com.rony.study.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@Service
class RestService() {
    private val log = LoggerFactory.getLogger(this.javaClass)

    fun callRestUrl(url: String): String {

        // HttpClient 생성
        val client = HttpClient.newHttpClient()

        // HttpRequest 생성
        val request: HttpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .build()

        val response = client.send(request, HttpResponse.BodyHandlers.ofString())

        return response.body()
    }
}
package dev.wigger.solar.util

import io.quarkus.logging.Log
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import org.eclipse.microprofile.config.inject.ConfigProperty
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

@ApplicationScoped
class Http {
    @Inject
    @ConfigProperty(name = "domain")
    private lateinit var domain: String
    
    fun smartfox(): String = try {
        HttpClient.newBuilder()
            .build()
            .send(HttpRequest.newBuilder().uri(URI.create("$domain/values.xml")).build(), HttpResponse.BodyHandlers.ofString())
            .body()
    } catch (e: Exception) {
        Log.error(e.message)
        "<values></values>"
    }
}

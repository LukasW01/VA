package dev.wigger.solar.controller

import dev.wigger.solar.util.Http

import io.quarkiverse.bucket4j.runtime.RateLimited
import io.quarkiverse.bucket4j.runtime.resolver.IpResolver
import io.quarkus.cache.CacheResult
import jakarta.enterprise.context.ApplicationScoped
import jakarta.inject.Inject
import jakarta.ws.rs.Consumes
import jakarta.ws.rs.GET
import jakarta.ws.rs.Path
import jakarta.ws.rs.Produces
import jakarta.ws.rs.core.MediaType

@ApplicationScoped
@Path("/")
@Produces(MediaType.APPLICATION_XML) @Consumes(MediaType.APPLICATION_XML)
@RateLimited(bucket = "solar", identityResolver = IpResolver::class)
class SolarController {
    @Inject
    lateinit var http: Http
    
    @GET
    @CacheResult(cacheName = "solar")
    fun getSolar(): String = http.smartfox()
}

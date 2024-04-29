package com.example.kotlinatm.configuration

import com.example.kotlinatm.entity.Atm
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer
import org.springframework.web.servlet.config.annotation.CorsRegistry
//
//open class ExposeEntityIdRestConfiguration : RepositoryRestConfigurer {
//    override fun configureRepositoryRestConfiguration(config: RepositoryRestConfiguration?, cors: CorsRegistry?) {
//        config!!.exposeIdsFor(Atm::class.java)
//    }
//}
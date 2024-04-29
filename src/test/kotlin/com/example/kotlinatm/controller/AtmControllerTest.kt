package com.example.kotlinatm.controller

import com.example.kotlinatm.service.AtmService
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc

@ExtendWith(SpringExtension::class)
@WebMvcTest(AtmController::class)
class AtmControllerTest (@Autowired val mockMvc: MockMvc) {

    //เวลา auto wires จะจำลอง service นั้นมาไว้ในนี้
    @MockBean
    lateinit var atmService: AtmService

    @Test
    fun `should return `(){

    }


}
package com.example.kotlinatm.controller

import com.example.kotlinatm.entity.*
import com.example.kotlinatm.service.AtmService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*
//import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["*"])
class AtmController {

    @Autowired
    lateinit var atmService: AtmService

    @PostMapping("/login")
    fun login(@RequestBody info: Login): ResponseEntity<Any>{
        return ResponseEntity.ok().body(atmService.login(info))
    }


    @PostMapping("/account")
    fun createAccount(@RequestBody info : Atm): ResponseEntity<Any> {
        return ResponseEntity.ok().body(atmService.createAccount(info))
    }

    @PutMapping("/account")
    fun updateAccount(@RequestBody info : Atm): ResponseEntity<Any> {
        return ResponseEntity.ok().body(atmService.updateAccount(info))
    }

    @GetMapping("/getAllAccount")
    fun getAllAccount(): ResponseEntity<Any> {
        return ResponseEntity.ok().body(atmService.getAllAccount())
    }


    @PostMapping("/account/{accountNo}")
    fun getAccount(@PathVariable("accountNo") accountNo : String): ResponseEntity<Any> {
        return ResponseEntity.ok().body(atmService.getAccount(accountNo))
    }

    @GetMapping("/getAmount/{accountNo}")
    fun getAmount(@PathVariable("accountNo") accountNo : String): ResponseEntity<Any> {
        return ResponseEntity.ok().body(atmService.getAmount(accountNo))
    }

//    @PostMapping("/withdraw")
    @PutMapping("/withdraw")
    fun withdraw(@RequestBody withdraw : WithAndDep): ResponseEntity<Any> {
        return ResponseEntity.ok().body(atmService.withdraw(withdraw))
    }

//    @PostMapping("/deposit")
    @PutMapping("/deposit")
    fun deposit(@RequestBody withdraw: WithAndDep): ResponseEntity<Any>{
        return ResponseEntity.ok().body(atmService.deposit(withdraw))
    }

    @PostMapping("/deleteAccount/{accountNo}")
    fun deleteAccount(@PathVariable("accountNo") accountNo : String): ResponseEntity<DeleteStatus> {
        return ResponseEntity.ok().body(atmService.deleteAccount(accountNo))
    }
}
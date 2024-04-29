package com.example.kotlinatm.repository

import com.example.kotlinatm.entity.Atm
import com.example.kotlinatm.entity.DeleteStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface AtmRepository : JpaRepository<Atm, Long> {
    fun findByAccountNoEquals(accountNo: String?): Atm

    fun findByUsernameAndPasswordEquals(username: String?, password: String?): Atm

//    fun findByAccountNo(accountNo: String?): Atm
//
//    fun deleteByAccountNo(accountNo: String?): Atm
}

package com.example.kotlinatm.entity

import jakarta.persistence.*
import java.math.BigDecimal


@Entity
@Table(name = "Account")
data class Atm(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    var id: Long? = null,

    @Column(name = "accountNo")
    var accountNo: String? = null,

//    @Column(name = "gender")
//    var gender: String? = null,

    @Column(name = "username")
    var username: String? = null,

    @Column(name = "password")
    var password: String? = null,

    @Column(name = "firstName")
    var firstName: String? = null,

    @Column(name = "lastName")
    var lastName: String? = null,

    @Column(name = "address")
    var address: String? = null,

    @Column(name = "tel")
    var tel: String? = null,

    @Column(name = "birthday")
    var birthday: String? = null,

    @Column(name = "age")
    var age: Int? = null,

    @Column(name = "status")
    var status: String? = null,

    @Column(name = "region")
    var region: String? = null,

    @Column(name = "color")
    var color: String? = null,

    @Column(name = "email")
    var email: String? = null,

    @Column(name = "amount")
    var amount: BigDecimal? = null,

    )

data class Login(
    var username: String? = null,
    var password: String? = null,
){
}
data class ReturnStatus(
    var status: Boolean? = null,
    var message: String? = null
){
}

data class ReturnAccount(
    var accountNo: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
    var gender: String? = null,
    var age: Int? = null,
    var tel: String? = null,
    var amount: String? = null,
){
}

data class ReturnCreate(
    var accountNo: String? = null,
    var firstName: String? = null,
    var lastName: String? = null,
){
}

data class ErrorCreate(
    var status: Boolean? = null,
    var message: String? = null,
){
}

data class WithAndDep(
    var amount: Double? = null,
    var accountNo: String? = null,
){
}

data class FormatWithAndDep(
    var message: String? = null,
    var strAmount: String? = null,
    var amount: BigDecimal? = null,
    var accountNo: String? = null,
){
}

data class FormatAmount(
    var amount: String? = null,
    var accountNo: String? = null,
){
}

data class DeleteStatus(
    var status: Boolean? = null,
    var message: String? = null,
    var accountNo: String? = null,
){
}

data class ErrorAmount(
    var message: String? = null,
    var accountNo: String? = null,
){
}


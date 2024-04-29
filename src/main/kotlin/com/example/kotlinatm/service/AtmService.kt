package com.example.kotlinatm.service

import com.example.kotlinatm.entity.*
import com.example.kotlinatm.repository.AtmRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.text.DecimalFormat
import java.util.Random

@Service
class AtmService {

    @Autowired
    lateinit var atmRepository: AtmRepository
//    lateinit var accountRepository: AccountRepository
    var returnStatus = ReturnStatus()
    var returnCreate = ReturnCreate()
    var errorCreate = ErrorCreate()

    //    val withAndDep = WithAndDep()
    val formatWithAndDep = FormatWithAndDep()
    val formatAmount = FormatAmount()
    val errorAmount = ErrorAmount()

    val formatter = DecimalFormat("###,###,###,###,###,###,###,###,###,##0.00")

    fun login(info : Login): Any{
        try {
            var user = info.username
            var pass = info.password
            var result = atmRepository.findByUsernameAndPasswordEquals(user, pass)
            returnStatus.status = true
            returnStatus.message = result.firstName + " " + result.lastName
            return result
        }catch (e: Exception){
            returnStatus.status = false
            returnStatus.message = "login failed: ${e.message}"
            return returnStatus
        }
    }

    fun createAccount(data: Atm): Any {
        try {
//            val gender = data.gender; data.gender = data.gender?.trim()
            val firstName = data.firstName; data.firstName = data.firstName?.trim()
            val lastName = data.lastName; data.lastName = data.lastName?.trim()
            val age = data.age
            data.tel = data.tel?.trim()
//            data.tel = data.tel?.trim()
            val amount = data.amount!!.toDouble()

//            if (gender != null && firstName != null && lastName != null && tel != null && age != null && amount != null) {
            if (firstName != null && lastName != null && age != null && amount != null) {
//                if (age >= 10 && amount >= 1000 && tel.length == 10) {

                if (age >= 10 && amount >= 1000) {
                    val random: Random = Random()
                    val randomNumber: Long = random.nextLong(1000000000L, 9999999999L)
                    data.accountNo = randomNumber.toString()
                    val roundAmount : Double = data.amount!!.toDouble()
                    val roundedNumber = BigDecimal(roundAmount).setScale(2, BigDecimal.ROUND_DOWN)

                    data.amount = roundedNumber

//                    try {
//                        val regex = Regex("[a-zA-Z\u0E00-\u0E7F ]+")
//                        var genderCheck = data.gender
//                        if (!(genderCheck!!.matches(regex))){
//                            errorCreate.status = false
//                            errorCreate.message = "กรุณากรอกข้อมูลเพศให้ถูกต้อง"
//                            return errorCreate
//                        }

                        // format telephone
//                        var castTel : Int = tel.toInt()
//                        val phoneNumber = data.tel
//                        val formattedPhoneNumber = phoneNumber!!.replaceFirst("(\\d{3})(\\d{3})(\\d{4})".toRegex(), "$1-$2-$3")
//                        data.tel = formattedPhoneNumber

                        var result = atmRepository.save(data)

                        returnCreate.accountNo = data.accountNo
                        returnCreate.firstName = data.firstName
                        returnCreate.lastName = data.lastName

                        returnStatus.status = true
                        returnStatus.message = "Created Account Successfully"

                        return returnStatus
//                    }catch (e:Exception){
//                        errorCreate.status = false
//                        errorCreate.message = "กรุณากรอกข้อมูลเบอร์โทรศัพท์ให้ถูกต้อง"
//                        return errorCreate
//                    }
                }
                else {
                    errorCreate.status = false
                    errorCreate.message = "เงื่อนไขการสมัครบัญชีไม่ถูกต้อง"
//                    if (tel.length != 10) {
//                        errorCreate.message = "กรุณากรอกข้อมูลเบอร์โทรศัพท์ให้ถูกต้อง"
//                    }else{
//                        errorCreate.message = "เงื่อนไขการสมัครบัญชีไม่ถูกต้อง"
//                    }
                    return errorCreate
                }
            }else{
                errorCreate.status = false
                errorCreate.message = "กรุณากรอกข้อมูลให้ครบถ้วน"
                return errorCreate
            }


        }catch(e:Exception) {
            errorCreate.status = false
            errorCreate.message = "กรุณากรอกข้อมูลให้ถูกต้อง"
            return errorCreate
        }
}

    fun updateAccount(data: Atm): Any {
        try {
            val firstName = data.firstName; data.firstName = data.firstName?.trim()
            val lastName = data.lastName; data.lastName = data.lastName?.trim()
            val age = data.age
            val amount = data.amount!!.toDouble()
            if (firstName != null && lastName != null && age != null && amount != null) {

                if (age >= 10 && amount >= 1000) {
                    val roundAmount: Double = data.amount!!.toDouble()
                    val roundedNumber = BigDecimal(roundAmount).setScale(2, BigDecimal.ROUND_DOWN)

                    data.amount = roundedNumber

                    var result = atmRepository.saveAndFlush(data)

                    returnStatus.status = true
                    returnStatus.message = "Update Account Successfully"

                    return returnStatus
                } else {
                    errorCreate.status = false
                    errorCreate.message = "เงื่อนไขการสมัครบัญชีไม่ถูกต้อง"
                    return errorCreate
                }


            } else {
                errorCreate.status = false
                errorCreate.message = "เงื่อนไขการสมัครบัญชีไม่ถูกต้อง"
            }

        } catch (e: Exception) {
            errorCreate.status = false
            errorCreate.message = "กรุณากรอกข้อมูลให้ถูกต้อง"
            return errorCreate
        }
        return errorCreate
    }

    fun getAllAccount():Any{
        return atmRepository.findAll()
    }



    fun getAccount(accountNo : String) : Any{
        try {
            val returnAccount = ReturnAccount()
            val trimAccountNo = accountNo.trim()
            val account = atmRepository.findByAccountNoEquals(trimAccountNo)

//            returnAccount.accountNo = account.accountNo
//            returnAccount.firstName = account.firstName
//            returnAccount.lastName = account.lastName
//            returnAccount.tel = account.tel

//            val formatAmt : String = formatter.format(account.amount).toString()
//            returnAccount.amount = "$formatAmt บาท"
//            returnAccount.amount = account.amount
            return account
        }catch (e: Exception){
            errorAmount.message = "ไม่มีเลขบัญชีในระบบ"
            errorAmount.accountNo = accountNo
            return errorAmount
        }
    }

    fun getAmount(accountNo : String) : Any{
        val trimAccountNo = accountNo.trim()
        try {
            val account = atmRepository.findByAccountNoEquals(trimAccountNo)
            val formatAmt : String = formatter.format(account.amount).toString()
            formatAmount.amount = "$formatAmt บาท"
            formatAmount.accountNo = trimAccountNo
            return formatAmount
        }catch (e: Exception){
            errorAmount.message = "ไม่มีเลขบัญชีในระบบ"
            errorAmount.accountNo = trimAccountNo
            return errorAmount
        }
    }

    fun withdraw(withdraw : WithAndDep) : Any{
        withdraw.accountNo = withdraw.accountNo?.trim()
        try {
            val account : Atm = atmRepository.findByAccountNoEquals(withdraw.accountNo)
            val with : Double? = withdraw.amount
            val totalAmount : Double =  account.amount!!.toDouble()
            if (with != null && totalAmount != null) {
                if ((totalAmount - with) < 0) {
                    errorAmount.message = "ยอดเงินของคุณไม่เพียงพอ"
                    errorAmount.accountNo = withdraw.accountNo
                    return errorAmount
                }
                if (with < 100){
                    errorAmount.message = "ถอนเงินขั้นต่ำ 100 บาท"
                    errorAmount.accountNo = withdraw.accountNo
                    return errorAmount
                }
            }
            if (with != null && totalAmount != null) {
                account.amount = (totalAmount - with).toBigDecimal()
                val roundAmount : Double = account.amount!!.toDouble()
                val roundedNumber = BigDecimal(roundAmount).setScale(2, BigDecimal.ROUND_DOWN)
                account.amount = roundedNumber
            }
            atmRepository.saveAndFlush(account)

            val formatAmt : String = formatter.format(account.amount).toString()
            formatWithAndDep.message = "ถอนเงินสำเร็จ"
            formatWithAndDep.strAmount = "$formatAmt บาท"
            formatWithAndDep.amount = account.amount
            formatWithAndDep.accountNo = account.accountNo
            return formatWithAndDep
        }catch(e : Exception){
            errorAmount.message = "ไม่มีเลขบัญชีในระบบ"
            errorAmount.accountNo = withdraw.accountNo
            return errorAmount
        }
    }

    fun deposit(withdraw : WithAndDep) : Any{
        withdraw.accountNo = withdraw.accountNo?.trim()
        try {
            val account : Atm = atmRepository.findByAccountNoEquals(withdraw.accountNo)
            val dep : Double? = withdraw.amount
            val totalAmount : Double =  account.amount!!.toDouble()
            if (dep != null && totalAmount != null) {
                if (dep < 1){
                    errorAmount.message = "ฝากเงินขั้นต่ำ 1 บาท"
                    errorAmount.accountNo = withdraw.accountNo
                    return errorAmount
                }
                account.amount = (totalAmount + dep).toBigDecimal()
                val roundAmount : Double = account.amount!!.toDouble()
                val roundedNumber = BigDecimal(roundAmount).setScale(2, BigDecimal.ROUND_DOWN)
                account.amount = roundedNumber
            }
            atmRepository.saveAndFlush(account)
            val formatAmt : String = formatter.format(account.amount).toString()
            formatWithAndDep.message = "ฝากเงินสำเร็จ"
            formatWithAndDep.strAmount = "$formatAmt บาท"
            formatWithAndDep.amount = account.amount
            formatWithAndDep.accountNo = account.accountNo
            return formatWithAndDep
        }catch (e : Exception){
            errorAmount.message = "ไม่มีเลขบัญชีในระบบ"
            errorAmount.accountNo = withdraw.accountNo
            return errorAmount
        }

    }

    fun deleteAccount(accountNo : String) : DeleteStatus{
        val deleteStatus = DeleteStatus()
        val trimAccountNo = accountNo.trim()
        try {
            val account : Atm = atmRepository.findByAccountNoEquals(trimAccountNo)
            if(account != null){
                atmRepository.delete(account)
                deleteStatus.status = true
                deleteStatus.message = "ลบบัญชีสำเร็จ"
                deleteStatus.accountNo = trimAccountNo
                return deleteStatus
            }
        } catch (e: Exception) {
            deleteStatus.status = false
            deleteStatus.message = "ลบบัญชีไม่สำเร็จ ไม่พบบัญชีในระบบ"
            deleteStatus.accountNo = trimAccountNo
            return deleteStatus
        }
        return deleteStatus
    }

//    fun checkValue(value: Any?): Boolean{
//        if (value is String){
//            return true
//        }else{
//            return false
//        }
//    }

}





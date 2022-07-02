package com.example.intefaces.v1.signup

import com.example.models.userinfo.SignUpDao
import com.example.models.userinfo.UserDataDao
import com.example.utils.createdAtTime
import com.example.utils.defaultId
import com.example.utils.userInfoTable

class UserSignUpImpl : UserSIgnUp {

    override fun getCreatedUser(data: SignUpDao): UserDataDao {
        val id = if (userInfoTable.isNotEmpty()) {
            userInfoTable.size + 1
        } else {
            defaultId
        }
        return UserDataDao(
            id, data.firstName?: "", data.lastName ?: "", data.email ?:"", data.phoneNumber ?:"",data.password ?: "",
            createdAtTime
        )
    }

    override fun updateList(data: UserDataDao) {
        userInfoTable.add(data)
    }

    override fun checkIfEmailExist(email: String): Boolean {
        val isEmailExit  = userInfoTable.filter {
            it.email == email
        }
        return isEmailExit.isEmpty()
    }

    val user = userInfoTable.associateBy { it.email }

}
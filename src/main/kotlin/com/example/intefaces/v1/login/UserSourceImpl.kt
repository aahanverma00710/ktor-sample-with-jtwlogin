package com.example.intefaces.v1.login

import com.example.models.userinfo.LoginUserInfo
import com.example.models.userinfo.UserDataDao
import com.example.utils.userInfoTable


class UserSourceImpl : UserSource {

    override fun findUserById(id: Int): UserDataDao? {
        val user = userInfoTable.find {
            it.id == id
        }
        return user
    }

    override fun findUserByCredentials(credential: LoginUserInfo): UserDataDao? {
        val user = userInfoTable.find {
            it.email == credential.email
        }
        return user
    }

    private val users = userInfoTable.associateBy { it.id }
    
    private val usersByEmail = userInfoTable.associateBy { it.email }

}
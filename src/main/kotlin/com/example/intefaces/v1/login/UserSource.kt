package com.example.intefaces.v1.login

import com.example.models.userinfo.LoginUserInfo
import com.example.models.userinfo.UserDataDao

interface UserSource {

    fun findUserById(id: Int): UserDataDao?

   fun findUserByCredentials(credential: LoginUserInfo): UserDataDao?

}
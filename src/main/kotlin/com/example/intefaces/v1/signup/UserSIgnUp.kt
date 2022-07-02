package com.example.intefaces.v1.signup

import com.example.models.userinfo.SignUpDao
import com.example.models.userinfo.UserDataDao

interface UserSIgnUp{

    fun getCreatedUser(data: SignUpDao) : UserDataDao

    fun updateList(data: UserDataDao)

    fun checkIfEmailExist(email:String): Boolean
}
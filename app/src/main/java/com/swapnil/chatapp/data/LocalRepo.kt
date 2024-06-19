package com.swapnil.chatapp.data

import com.swapnil.chatapp.helpers.DataStoreUtil

class LocalRepo(private val dataStoreUtil: DataStoreUtil) {
    suspend fun onLoggedIn(){
        dataStoreUtil.setData("isLoggedIn",true)
    }

    suspend fun isLoggedIn(): Boolean {
        return dataStoreUtil.getData<Boolean>("isLoggedIn")?:false
    }
}
package com.swapnil.chatapp.data.remote

import com.google.firebase.Firebase
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.firestore
import com.swapnil.chatapp.data.remote.FirestoreCollections.usersColl
import com.swapnil.chatapp.domain.model.User
import kotlinx.coroutines.tasks.await

class UserRepo {
    suspend fun saveUser(user: User) {
        Firebase.firestore
            .usersColl()
            .add(user)
            .await()
    }

    suspend fun getUserWithEmail(email: String): User? {
        return Firebase.firestore
            .usersColl()
            .whereEqualTo(User::email.name, email)
            .get()
            .await()
            .toObjects(User::class.java)
            .firstOrNull()
    }
}
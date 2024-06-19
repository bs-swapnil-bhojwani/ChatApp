package com.swapnil.chatapp.data.remote

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreCollections {
    fun FirebaseFirestore.usersColl() = collection("users")
}
package com.protompany.protoconf.network

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.protompany.protoconf.model.Author
import com.protompany.protoconf.model.Conference

class FirestoreService {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    val settings = FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build()

    init {
        firebaseFirestore.firestoreSettings = settings
    }

    fun getAuthors(callback: Callback<List<Author>>) {
        firebaseFirestore.collection("author")
            .orderBy("category")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    var list = result.toObjects(Author::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun getSchedule(callback: Callback<List<Conference>>) {
        firebaseFirestore.collection("conferences")
            .get()
            .addOnSuccessListener { result ->
                for (doc in result) {
                    var list = result.toObjects(Conference::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }
}
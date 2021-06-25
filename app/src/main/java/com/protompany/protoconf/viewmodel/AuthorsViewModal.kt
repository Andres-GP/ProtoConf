package com.protompany.protoconf.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.protompany.protoconf.model.Author
import com.protompany.protoconf.network.Callback
import com.protompany.protoconf.network.FirestoreService
import java.lang.Exception

class AuthorsViewModal: ViewModel() {
    val firestoreService = FirestoreService()
    var listAuthors: MutableLiveData<List<Author>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()

    fun refresh(){
        getAuthorFromFirebase()
    }
    fun getAuthorFromFirebase() {
        firestoreService.getAuthors(object: Callback<List<Author>> {
            override fun onSuccess(result: List<Author>?) {
                processFinished()
            }

            override fun onFailed(exception: Exception) {
                processFinished()
            }
        })
    }
    fun processFinished() {
        isLoading.value = true
    }
}
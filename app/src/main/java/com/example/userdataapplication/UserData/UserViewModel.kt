package com.example.userdataapplication.UserData

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application : Application) : AndroidViewModel(application) {

     val readAllUsers : LiveData<List<User>>
    private val repository : UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
        readAllUsers =repository.readallusers
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }

    fun updateUser(user : User){
        viewModelScope.launch (Dispatchers.IO){
            repository.updateUser(user)
        }
    }
    fun deleteUser(user : User){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteUser(user)
        }
    }
    fun deleteAllUsers(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteAllUsers()
        }
    }

}
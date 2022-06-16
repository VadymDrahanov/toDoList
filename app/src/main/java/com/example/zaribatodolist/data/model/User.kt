package com.example.zaribatodolist.data.model

data class User(val email: String, val tasks: ArrayList<Task>?, val name: String){
    constructor(uid: String, email: String, tasks: ArrayList<Task>, name: String) : this(email, tasks, name )
}
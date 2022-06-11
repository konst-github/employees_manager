package com.tiani.employeesmanager.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Employee (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")            var id: Long = 0,

    @ColumnInfo(name = "first_name")    var firstName: String = "",
    @ColumnInfo(name = "last_name")     var lastName: String = "",
    @ColumnInfo(name = "age")           var age: Int = 0,
    @ColumnInfo(name = "gender")        var gender: String = "",
)
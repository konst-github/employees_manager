package com.tiani.employeesmanager.repository.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Address (
    @PrimaryKey(autoGenerate = true)    var id: Long = 0,
    @ColumnInfo(name = "employee_id")   var employeeId: Long = 0,
    /*
    * Address is represented as a single string for not to overcomplicate UI and logic
    * with single input fields for each part of an address - like street, building, city, country etc.
    * */
    @ColumnInfo(name = "address")       var address: String = ""
)
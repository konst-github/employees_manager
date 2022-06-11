package com.tiani.employeesmanager.repository.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

@Entity
data class EmployeeFull (

    @Embedded val employee: Employee,

    /*
    * Each Employee can have a couple of addresses.
    * This gives us one-to-many relationship of Employee entity with Address entity in DB.
    * For not to make UI too complicated, app supports up to 3 address fields.
    * */
    @Relation(
        parentColumn = "id",
        entityColumn = "employee_id"
    )
    val addresses: List<Address>
)
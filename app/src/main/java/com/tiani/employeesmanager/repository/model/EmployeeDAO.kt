package com.tiani.employeesmanager.repository.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EmployeeDAO {

    @Transaction
    @Query("SELECT * FROM Employee")
    fun getAllEmployees(): LiveData<List<EmployeeFull>>

    @Transaction
    @Query("SELECT * FROM Employee WHERE id = :id")
    fun getEmployee(id: Long): LiveData<EmployeeFull>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(employee: Employee): Long

    @Query("DELETE FROM Employee WHERE id = :id")
    fun delete(id: Long)
}
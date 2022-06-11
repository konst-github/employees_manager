package com.tiani.employeesmanager.repository.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AddressDAO {

    // 1: Select all addresses for a single employee
    @Query("SELECT * FROM Address WHERE employee_id = :id")
    fun getAll(id: Long): List<Address>

    // 2: Insert address
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(address: Address)

    // 3: Delete
    @Query("DELETE FROM Address WHERE employee_id = :employeeId")
    fun deleteAll(employeeId: Long)

    @Query("DELETE FROM Address WHERE employee_id = :id")
    fun delete(id: Int)
}
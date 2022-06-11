package com.tiani.employeesmanager.repository.model

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Employee::class, Address::class], version = 1, exportSchema = false)
abstract class EmployeesDatabase : RoomDatabase() {

    abstract fun employeeDao(): EmployeeDAO
    abstract fun addressDao(): AddressDAO

    companion object {
        private val lock = Any()
        private const val DB_NAME = "Employees.db"
        private var INSTANCE: EmployeesDatabase? = null

        fun getInstance(application: Application): EmployeesDatabase {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE =
                        Room.databaseBuilder(
                            application,
                            EmployeesDatabase::class.java, DB_NAME
                        )
                            .allowMainThreadQueries()
                            .build()
                }
                return INSTANCE!!
            }
        }
    }
}
package com.tiani.employeesmanager.fragments.employees_list

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.tiani.employeesmanager.application.EmployeesManagerApp
import com.tiani.employeesmanager.repository.model.EmployeeFull

class EmployeesListViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = getApplication<EmployeesManagerApp>().getRepository()
    private val employeesList = MediatorLiveData<List<EmployeeFull>>()

    init { getAllEmployees() }

    fun getEmployeesList(): LiveData<List<EmployeeFull>> = employeesList

    private fun getAllEmployees() {
        employeesList.addSource(repository.getAllEmployees()) { employees ->
            employeesList.postValue(employees)
        }
    }
}
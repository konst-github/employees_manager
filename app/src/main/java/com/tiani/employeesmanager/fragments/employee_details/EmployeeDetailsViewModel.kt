package com.tiani.employeesmanager.fragments.employee_details

import android.app.Application
import androidx.lifecycle.*
import com.tiani.employeesmanager.application.EmployeesManagerApp
import com.tiani.employeesmanager.repository.model.EmployeeFull
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class EmployeeDetailsViewModel(application: Application) : AndroidViewModel(application)  {

    private val repository = getApplication<EmployeesManagerApp>().getRepository()
    private val employeeId = MutableLiveData<Long>()

    fun getEmployeeDetails(id: Long): LiveData<EmployeeFull> {
        employeeId.value = id
        return Transformations.switchMap(employeeId) { ID -> repository.getEmployee(ID) }
    }

    fun addEmployee(employeeFull: EmployeeFull) {
        repository.insertEmployee(employeeFull)
    }

    fun deleteEmployee(employeeId: Long) {
        repository.deleteEmployee(employeeId)
    }
}
package com.tiani.employeesmanager.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.tiani.employeesmanager.repository.model.AddressDAO
import com.tiani.employeesmanager.repository.model.EmployeeDAO
import com.tiani.employeesmanager.repository.model.EmployeeFull
import com.tiani.employeesmanager.repository.model.EmployeesDatabase

class Repository(application: Application) {

    private val employeeDAO: EmployeeDAO
    private val addressDAO: AddressDAO

    init {
        val database = EmployeesDatabase.getInstance(application)
        employeeDAO = database.employeeDao()
        addressDAO = database.addressDao()
    }

    /*
     * Here and in other places - single-expression function syntax is used only for methods
     * which explicitly return some object
     * */
    fun getAllEmployees(): LiveData<List<EmployeeFull>> = employeeDAO.getAllEmployees()

    fun getEmployee(id: Long): LiveData<EmployeeFull> = employeeDAO.getEmployee(id)

    /*
    * Since there is still an issue in Room, when insert accepts only objects with @Entity annotation,
    * but it wont accept object with @Repation annotation
    * (error: Entities cannot have relations. public final class EmployeeFull) ->
    * insert is done as a workaround through POJO EmployeeFull class.
    * It is also possible to avoid usage of EmployeeFull, but it will require a bit more coding,
    * plus when this issue will be fixed - we'll need to modify only insert-related code.
    * */
    fun insertEmployee(employeeFull: EmployeeFull) {
        val newId = employeeDAO.insert(employeeFull.employee)
        val addressesCurrent = addressDAO.getAll(newId)
        // TODO: check how to do without the loop - iterations
        for (index in employeeFull.addresses.indices) {
            val item = employeeFull.addresses[index]
            item.id = if(addressesCurrent.size > index) addressesCurrent[index].id else 0
            item.employeeId = newId
            addressDAO.insert(item)
        }
    }

    /*
    * Here and in other places - single-expression function syntax is not used for 'void' methods
    * even though compiler allows such approach, mostly to avoid confusion during reading.
    * */
    fun deleteEmployee(id: Long) {
        employeeDAO.delete(id)
    }
}
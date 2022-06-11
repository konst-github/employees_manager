package com.tiani.employeesmanager.application

import android.app.Application
import com.tiani.employeesmanager.repository.Repository

class EmployeesManagerApp : Application() {

    fun getRepository() = Repository(this)

}
package com.tiani.employeesmanager.fragments.employee_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.tiani.employeesmanager.R
import com.tiani.employeesmanager.databinding.FragmentEmployeeDetailsBinding
import com.tiani.employeesmanager.repository.model.Address
import com.tiani.employeesmanager.repository.model.Employee
import com.tiani.employeesmanager.repository.model.EmployeeFull
import java.util.*

class EmployeeDetailsFragment : Fragment() {

    private lateinit var viewModel: EmployeeDetailsViewModel
    private lateinit var binding: FragmentEmployeeDetailsBinding
    private var employeeId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeDetailsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_employee_details, container, false)

        binding = FragmentEmployeeDetailsBinding.bind(root)
        binding.saveButton.setOnClickListener { button ->
            saveEmployee()
            button.findNavController().navigateUp()
        }
        binding.deleteButton.setOnClickListener { button ->
            deleteEmployee()
            button.findNavController().navigateUp()
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        employeeId = arguments?.getLong("employeeId") ?: 0

        binding.deleteButton.visibility = if(employeeId == 0L) GONE else VISIBLE

        if(employeeId != 0L) {
            employeeId.let {
                viewModel.getEmployeeDetails(employeeId)
                    .observe(viewLifecycleOwner, { employeeDetails ->
                        updateEmployeeDetails(employeeDetails)
                    })
            }
        }
    }

    private fun saveEmployee() {
        val addresses = listOf(
            Address(0, employeeId, binding.address1.editText?.text.toString()),
            Address(0, employeeId, binding.address2.editText?.text.toString()),
            Address(0, employeeId, binding.address3.editText?.text.toString())
        )
        val age = binding.age.editText?.text.toString()

        val employee = EmployeeFull(
            Employee(employeeId,
                binding.firstName.editText?.text.toString(),
                binding.lastName.editText?.text.toString(),
                if(age.isEmpty()) 0 else age.toInt(),
                binding.gender.editText?.text.toString()),
            addresses)

        viewModel.addEmployee(employee)
    }

    private fun deleteEmployee() {
        viewModel.deleteEmployee(employeeId)
    }

    private fun updateEmployeeDetails(employee: EmployeeFull) {

        binding.firstName.editText?.setText(employee.employee.firstName)
        binding.lastName.editText?.setText(employee.employee.lastName)
        binding.gender.editText?.setText(employee.employee.gender)
        binding.age.editText?.setText(String.format(Locale.ENGLISH, "%d", employee.employee.age))

        binding.address1.editText?.setText(
            if (employee.addresses.isNotEmpty()) employee.addresses[0].address else ""
        )
        binding.address2.editText?.setText(
            if (employee.addresses.size > 1) employee.addresses[1].address else ""
        )
        binding.address3.editText?.setText(
            if (employee.addresses.size > 2) employee.addresses[2].address else ""
        )
    }
}
package com.tiani.employeesmanager.fragments.employees_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.tiani.employeesmanager.R
import com.tiani.employeesmanager.databinding.FragmentEmployeesListBinding
import com.tiani.employeesmanager.repository.model.EmployeeFull

class EmployeesListFragment : Fragment(),
    EmployeesListAdapter.OnItemClickListener {

    private lateinit var viewModel: EmployeesListViewModel
    private lateinit var binding: FragmentEmployeesListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeesListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_employees_list, container, false)

        binding = FragmentEmployeesListBinding.bind(root)
        binding.addEmployeeButton.setOnClickListener { button ->
            val action =
                EmployeesListFragmentDirections.actionEmployeesListFragmentToEmployeeDetailsFragment(0)
            button.findNavController().navigate(action)
        }

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getEmployeesList().observe(viewLifecycleOwner, { employees ->
            employees?.let { populateEmployeesList(employees) }
        })
    }

    private fun populateEmployeesList(list: List<EmployeeFull>) {
        binding.employeesRecyclerView.adapter = EmployeesListAdapter(list, this)
    }

    override fun onItemClick(employee: EmployeeFull, itemView: View) {
        val action =
            EmployeesListFragmentDirections.actionEmployeesListFragmentToEmployeeDetailsFragment(employee.employee.id)
        view?.findNavController()?.navigate(action)
    }
}
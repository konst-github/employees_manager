package com.tiani.employeesmanager.fragments.employees_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tiani.employeesmanager.R
import com.tiani.employeesmanager.databinding.CellEmployeeBinding
import com.tiani.employeesmanager.repository.model.EmployeeFull
import java.util.*

class EmployeesListAdapter(
    private val dataSource: List<EmployeeFull>,
    private val clickListener: OnItemClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.cell_employee, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ListViewHolder).bind(dataSource[position], clickListener)
    }

    override fun getItemCount(): Int = dataSource.size

    class ListViewHolder (
        itemView: View,
        ) : RecyclerView.ViewHolder(itemView) {

        fun bind(employee: EmployeeFull, listener: OnItemClickListener) = with(itemView) {
            val binder = CellEmployeeBinding.bind(itemView)
            binder.firstName.text =
                String.format(Locale.ENGLISH, "%s %s",
                    employee.employee.firstName, employee.employee.lastName)
            binder.age.text = String.format(Locale.ENGLISH, "%d y.o.", employee.employee.age)
            binder.gender.text = employee.employee.gender
            binder.address.text = if(employee.addresses.isEmpty()) "" else employee.addresses[0].address

            setOnClickListener { listener.onItemClick(employee, it) }
        }
    }

    interface OnItemClickListener {
        fun onItemClick(employee: EmployeeFull, itemView: View)
    }
}
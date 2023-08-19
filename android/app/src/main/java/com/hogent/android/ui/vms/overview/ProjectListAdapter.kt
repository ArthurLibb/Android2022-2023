package com.hogent.android.ui.vms.overview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.hogent.android.R
import com.hogent.android.data.entities.Project
import com.hogent.android.network.dtos.response.VirtualmachineIndex
import com.hogent.devOps_Android.ui.vms.overview.VirtualMachineListAdapter
import timber.log.Timber

class ProjectListAdapter(
    private val projectList: List<Project>,
    private val virtualmachineList: List<VirtualmachineIndex>?,
    private val context: Context?,
) : RecyclerView.Adapter<ProjectListAdapter.ViewHolder>() {


    private var newvirtualMachineList = mutableListOf<VirtualmachineIndex>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val textView1: TextView = itemView.findViewById(R.id.textView1)
        val recyclerView: RecyclerView = itemView.findViewById(R.id.virtual_machine_recyclerview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.project_container, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val project = projectList[position]
        holder.textView1.text = project.name;

        if(project.id > 0) {
            holder.recyclerView.layoutManager = LinearLayoutManager(context)
            filterVirtualMachines(project.id)
            holder.recyclerView.adapter = VirtualMachineListAdapter(newvirtualMachineList)
            holder.textView1.text = project.name
        }
    }

    override fun getItemCount(): Int {
        return projectList.size
    }


    private fun filterVirtualMachines(projectId: Int) {
        Timber.e("Previous length of vms inside project with id: %d   =  %d",projectId, newvirtualMachineList.size)
        newvirtualMachineList.clear()

        //de nieuw gecreërde VM zit niet in deze lijst
        virtualmachineList?.forEach { i ->
                newvirtualMachineList.add(i)
        }
        Timber.e("After refreshing project with id: %d   =  %d", projectId, newvirtualMachineList.size)

    }
}

package com.neupanesushant.rxjava_subject_application

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neupanesushant.rxjava_subject_application.databinding.RvItemLayoutBinding
import com.neupanesushant.rxjava_subject_application.domain.Todo

class RecyclerViewAdapter(val context : Context, val list : List<Todo>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(binding: RvItemLayoutBinding) : RecyclerView.ViewHolder(binding.root){
        val id = binding.tvId
        val title = binding.tvTitle
        val completionStatus = binding.tvCompletionStatus

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentObj = list.get(position)
        holder.id.text = currentObj.id.toString()
        holder.title.text = currentObj.title
        holder.completionStatus.text = getStatus(currentObj.completed)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun getStatus(boolean : Boolean) : String {
        if(boolean){
            return CompletionType.COMPLETE
        }else{
            return CompletionType.INCOMPLETE
        }
    }
}
package com.neupanesushant.bankingdashboardclone.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.neupanesushant.bankingdashboardclone.R
import com.neupanesushant.bankingdashboardclone.databinding.LastWeekDataRecyclerviewBinding
import com.neupanesushant.bankingdashboardclone.model.LastWeek

class LastWeekDataAdapter(val context : Context, val itemsList : ArrayList<LastWeek>) : RecyclerView.Adapter<LastWeekDataAdapter.ViewHolder>() {
    inner class ViewHolder(binding : LastWeekDataRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root) {
        val image = binding.ivCompanyLogo
        val name = binding.tvCompanyName
        val amount = binding.tvAmount
        val date = binding.tvUpdateDate
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LastWeekDataRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentObj = itemsList.get(position)

        Glide.with(context).load(currentObj.image).circleCrop().into(holder.image)
        val amountString = "$ ${currentObj.price}"
        holder.amount.text = amountString
        if(currentObj.price >= 0){
            holder.amount.setBackgroundResource(R.drawable.positive_price_background)
            holder.amount.setTextColor(ContextCompat.getColor(context, R.color.dark_green))
        }

        holder.date.text = currentObj.date
        holder.name.text = currentObj.name

    }

    override fun getItemCount(): Int {
        return itemsList.size
    }
}
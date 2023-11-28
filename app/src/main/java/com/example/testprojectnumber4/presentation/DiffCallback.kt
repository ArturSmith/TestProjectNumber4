package com.example.testprojectnumber4.presentation

import android.util.Log
import androidx.recyclerview.widget.DiffUtil
import com.example.testprojectnumber4.data.pojo.Payment

class DiffCallback : DiffUtil.ItemCallback<Payment>() {
    override fun areItemsTheSame(oldItem: Payment, newItem: Payment): Boolean {
        return oldItem.hashCode() == newItem.hashCode()
    }

    override fun areContentsTheSame(oldItem: Payment, newItem: Payment): Boolean {
        return oldItem == newItem
    }
}
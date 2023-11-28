package com.example.testprojectnumber4.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testprojectnumber4.R

class PaymentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val title: TextView = itemView.findViewById(R.id.title)
    val paymentId: TextView = itemView.findViewById(R.id.payment_id)
    val amount: TextView = itemView.findViewById(R.id.amount)
    val created: TextView = itemView.findViewById(R.id.created)

}
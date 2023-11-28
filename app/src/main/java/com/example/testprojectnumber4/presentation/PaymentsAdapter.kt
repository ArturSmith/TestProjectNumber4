package com.example.testprojectnumber4.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.testprojectnumber4.R
import com.example.testprojectnumber4.data.pojo.Payment

class PaymentsAdapter : ListAdapter<Payment, PaymentViewHolder>(DiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.payment_card,
            parent,
            false
        )
        return PaymentViewHolder(view)
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val payment = getItem(position)
        val amountText =
            if (payment.amount == null) "- Information not available" else payment.amount
        val createdText =
            if (payment.created == null) "- Information not available" else payment.created
        holder.apply {
            title.text = payment.title
            paymentId.text = String.format(
                itemView.context.resources.getString(R.string.payment_id),
                payment.id
            )
            amount.text = String.format(
                itemView.context.resources.getString(R.string.amount),
                amountText
            )
            created.text = String.format(
                itemView.context.resources.getString(R.string.created),
                createdText
            )
        }
    }
}
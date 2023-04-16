package com.zeeland.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zeeland.addressbook.R
import com.zeeland.model.Contact

class ContactAdapter(
    private val contacts: ArrayList<Contact>,
    private val listener: OnItemClickListener
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_name)
        val phone: TextView = itemView.findViewById(R.id.tv_phone)
        val address: TextView = itemView.findViewById(R.id.tv_address)
        val editButton: Button = itemView.findViewById(R.id.btn_edit)
        val deleteButton: Button = itemView.findViewById(R.id.btn_delete)
    }

    interface OnItemClickListener {
        fun onItemClick(contact: Contact)
        fun onEditClick(contact: Contact, position: Int)
        fun onDeleteClick(contact: Contact, position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val currentContact = contacts[position]

        holder.name.text = currentContact.name
        holder.phone.text = currentContact.phone
        holder.address.text = currentContact.address

        holder.editButton.setOnClickListener {
            listener.onEditClick(currentContact, position)
        }
        holder.deleteButton.setOnClickListener {
            listener.onDeleteClick(currentContact, position)
        }
        holder.itemView.setOnClickListener {
            listener.onItemClick(currentContact)
        }
    }

    override fun getItemCount(): Int {
        return contacts.size
    }


}

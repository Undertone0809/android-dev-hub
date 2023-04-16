package com.zeeland.addressbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zeeland.model.Contact
import com.zeeland.adapter.ContactAdapter

class ContactsActivity : AppCompatActivity(), ContactAdapter.OnItemClickListener {

    private val contactList = ArrayList<Contact>()
    private lateinit var contactAdapter: ContactAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)

        contactAdapter = ContactAdapter(contactList, this)

        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            layoutManager = LinearLayoutManager(this@ContactsActivity)
            adapter = contactAdapter
        }

        val addButton = findViewById<Button>(R.id.btn_add)
        addButton.setOnClickListener {
            addContact()
        }
    }

    private fun addContact() {
        val intent = Intent(this, ContactEditActivity::class.java).apply {
            putExtra(ContactEditActivity.EXTRA_MODE, ContactEditActivity.MODE_NEW)
        }
        startActivityForResult(intent, REQUEST_NEW_CONTACT)
    }

    override fun onItemClick(contact: Contact) {
        // do nothing
    }

    override fun onEditClick(contact: Contact, position: Int) {
        val intent = Intent(this, ContactEditActivity::class.java).apply {
            putExtra(ContactEditActivity.EXTRA_MODE, ContactEditActivity.MODE_EDIT)
            putExtra(ContactEditActivity.EXTRA_CONTACT, contact)
        }
        startActivityForResult(intent, REQUEST_EDIT_CONTACT)
    }

    override fun onDeleteClick(contact: Contact, position: Int) {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.confirm_delete))
            .setPositiveButton(getString(R.string.confirm)) { _, _ ->
                contactList.remove(contact)
                contactAdapter.notifyItemRemoved(position)
            }
            .setNegativeButton(getString(R.string.cancel), null)
            .create()
            .show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                REQUEST_NEW_CONTACT -> {
                    val contact = data?.getParcelableExtra<Contact>(ContactEditActivity.EXTRA_CONTACT) ?: return
                    contactList.add(contact)
                    contactAdapter.notifyItemInserted(contactList.size - 1)
                }
                REQUEST_EDIT_CONTACT -> {
                    val contact = data?.getParcelableExtra<Contact>(ContactEditActivity.EXTRA_CONTACT) ?: return
                    val position = data.getIntExtra(ContactEditActivity.EXTRA_POSITION, -1)
                    if (position == -1) return
                    contactList[position] = contact
                    contactAdapter.notifyItemChanged(position)
                }
            }
        }
    }

    companion object {
        const val REQUEST_NEW_CONTACT = 1
        const val REQUEST_EDIT_CONTACT = 2
    }

}

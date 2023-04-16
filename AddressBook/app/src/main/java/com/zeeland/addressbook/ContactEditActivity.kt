package com.zeeland.addressbook

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zeeland.model.Contact

class ContactEditActivity : AppCompatActivity() {

    private var mode = 0
    private var position = -1
    private var contact: Contact? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_edit)

        intent?.let {
            mode = it.getIntExtra(EXTRA_MODE, MODE_NEW)
            contact = it.getParcelableExtra(EXTRA_CONTACT)
            position = it.getIntExtra(EXTRA_POSITION, -1)
        }

        val nameEditText = findViewById<EditText>(R.id.et_name)
        val phoneEditText = findViewById<EditText>(R.id.et_phone)
        val addressEditText = findViewById<EditText>(R.id.et_address)

        if (mode == MODE_EDIT && contact != null) {
            nameEditText.setText(contact!!.name)
            phoneEditText.setText(contact!!.phone)
            addressEditText.setText(contact!!.address)
        }

        val saveButton = findViewById<Button>(R.id.btn_save)
        saveButton.text =
            if (mode == MODE_EDIT) getString(R.string.update) else getString(R.string.add)
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val phone = phoneEditText.text.toString()
            val address = addressEditText.text.toString()

            if (name.isEmpty() || phone.isEmpty() || address.isEmpty()) {
                Toast.makeText(this, getString(R.string.empty_input), Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val resultIntent = Intent().apply {
                putExtra(EXTRA_CONTACT, Contact(name, phone, address))
                putExtra(EXTRA_POSITION, position)
            }

            setResult(RESULT_OK, resultIntent)
            finish()
        }

        val cancelButton = findViewById<Button>(R.id.btn_cancel)
        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    companion object {
        const val EXTRA_MODE = "mode"
        const val EXTRA_CONTACT = "contact"
        const val EXTRA_POSITION = "position"

        const val MODE_NEW = 0
        const val MODE_EDIT = 1
    }

}

package com.example.agenda

import android.content.Context
import com.example.agenda.MainActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import java.io.FileOutputStream

class EditorActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    lateinit var editTextNameInput: EditText
    lateinit var editTextAddressInput: EditText
    lateinit var editTextPhoneInput: EditText
    lateinit var spinnerInput: Spinner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editor)

        editTextNameInput = findViewById(R.id.editTextNameInput)
        editTextAddressInput = findViewById(R.id.editTextAddressInput)
        editTextPhoneInput = findViewById(R.id.editTextPhoneInput)
        spinnerInput = findViewById(R.id.spinnerInput)

        setSpinner()

        if (intent.extras != null) {
            var selectedUserIndex = intent.extras!!.get("selectedUserIndex").toString().toInt()

            // Setting selected user's data in all input fields
            setUserDataInFields(selectedUserIndex)
            Log.d("Bla", "bla")
        }
    }



    fun setSpinner(): Unit{
        val spinner: Spinner = findViewById(R.id.spinnerInput)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.contact_type_list,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
        }

    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {
        //contactType = position
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {
        TODO()
    }

    fun saveButtonPressed(v: View): Unit{
        if(editTextNameInput.text.isEmpty() or editTextNameInput.text.isBlank()){
            return
        }
        if(editTextAddressInput.text.isEmpty() or editTextAddressInput.text.isBlank()){
            return
        }
        if(editTextPhoneInput.text.isEmpty() or editTextPhoneInput.text.isBlank()){
            return
        }

        if(intent.extras?.get("selectedUserIndex") != null){
            var index = intent.extras!!.get("selectedUserIndex").toString().toInt()
            Model.itemList[index].name = editTextNameInput.text.toString()
            Model.itemList[index].address = editTextAddressInput.text.toString()
            Model.itemList[index].phone = editTextPhoneInput.text.toString()
            Model.itemList[index].contactType = spinnerInput.selectedItemPosition
            saveFile()
            finish()
        }else{
            Model.itemList.add(User(editTextNameInput.text.toString(), editTextAddressInput.text.toString(), editTextPhoneInput.text.toString(), spinnerInput.selectedItemPosition))
            finish()
        }
    }

    fun saveFile(){
        //onPause()
        var fileOutputStream: FileOutputStream
        try {
            fileOutputStream = openFileOutput("lista.txt", Context.MODE_PRIVATE)

            for (item in Model.itemList){
                fileOutputStream.write("${item.name}\n".toByteArray())
                fileOutputStream.write("${item.address}\n".toByteArray())
                fileOutputStream.write("${item.phone}\n".toByteArray())
                fileOutputStream.write("${item.contactType}\n".toByteArray())
            }

            fileOutputStream.close()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun setUserDataInFields(index: Int): Unit{
        editTextNameInput.setText(Model.itemList[index].name.toString())
        editTextAddressInput.setText(Model.itemList[index].address.toString())
        editTextPhoneInput.setText(Model.itemList[index].phone.toString())
        spinnerInput.setSelection(Model.itemList[index].contactType)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}

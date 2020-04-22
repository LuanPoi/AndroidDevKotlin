package com.example.agenda

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.io.*


class MainActivity : AppCompatActivity() {
    lateinit var listViewUsers: ListView
    lateinit var buttonAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(File("lista.txt").exists()){
            loadFile()
        }

        listViewUsers = findViewById(R.id.listViewUsers)
        updateList()

        listViewUsers.setOnItemClickListener { parent, view, position, id ->
            var i = Intent(this, EditorActivity::class.java)
            i.putExtra("selectedUserIndex", position)
            startActivity(i)
        }

    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    fun updateList(){
        var listOfUsers = Model.itemList.map { User ->
            (User.name
                    + "\n" + User.address
                    + "\n" + User.phone
                    + "\n" + User.contactType)
        }

        var adapter: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            listOfUsers

        )
        listViewUsers.adapter = adapter
    }

    fun loadFile(){
        //onCreate()
        var lista: ArrayList<String> = ArrayList<String>()
        try {
            var fileInputStream: FileInputStream? = null
            fileInputStream = openFileInput("lista.txt")
            var inputStreamReader: InputStreamReader = InputStreamReader(fileInputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            val stringBuilder: StringBuilder = StringBuilder()
            var text: String? = null

            var counter: Int = 0
            while ({ text = bufferedReader.readLine(); text }() != null) {
                counter++
                if(text != null){
                    lista.add(text!!)
                }
                if(counter == 4){
                    Model.itemList.add(User(lista[0], lista[1], lista[2], lista[3].toInt()))
                    lista.removeAll(lista)
                }
            }
            Log.d("bla","${stringBuilder.toString()}")
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    fun floatingActionButtonAddPressed(v: View): Unit{
        startActivity(Intent(this, EditorActivity::class.java))
    }
}

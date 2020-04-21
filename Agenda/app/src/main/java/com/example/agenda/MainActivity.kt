package com.example.agenda

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter


class MainActivity : AppCompatActivity() {
    lateinit var listViewUsers: ListView
    lateinit var buttonAdd: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewUsers = findViewById(R.id.listViewUsers)
        updateList()

        listViewUsers.setOnItemClickListener { parent, view, position, id ->
            var i = Intent(this, EditorActivity::class.java)
            i.putExtra("selectedUserIndex", position)
            startActivity(i)
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_INTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Log.d("teste","Deu merda dnv")
        }

    }

    override fun onResume() {
        super.onResume()
        updateList()
    }

    fun floatingActionButtonAddPressed(v: View): Unit{
        startActivity(Intent(this, EditorActivity::class.java))
    }

    override fun onPause() {
        super.onPause()
        Log.d("Checkpoint", "OnPause reached")
        Model.saveFile()
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
}

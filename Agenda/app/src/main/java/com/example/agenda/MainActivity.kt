package com.example.agenda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    lateinit var listViewUsers: ListView
    lateinit var floatingActionButtonAdd: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listViewUsers = findViewById(R.id.listViewUsers)
        floatingActionButtonAdd = findViewById(R.id.floatingActionButtonAdd)

        Model.itemList.add(User("Luan", "Dra.Otillia dos Santos França, 103", "43999713885", 1.toUByte()))
        Model.itemList.add(User("Jeferson", "Dra.Otillia dos Santos França, 103", "43999713885", 1.toUByte()))
        Model.itemList.add(User("Arthur", "Dra.Otillia dos Santos França, 103", "43999713885", 1.toUByte()))
        Model.itemList.add(User("Zacarias", "Dra.Otillia dos Santos França, 103", "43999713885", 1.toUByte()))

//        var listOfUsers = Model.itemList.map { User ->
//            (User.name
//                    + "\n" + User.address
//                    + "\n" + User.phone
//                    + "\n" + User.contactType)
//        }
//
//        var listOfBodies = Model.itemList.map { User ->  }
//        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
//            this,
//            android.R.layout.simple_list_item_1,
//            android.R.id.text1,
//            listOfUsers
//
//        )
//        listViewUsers.adapter = adapter

        listViewUsers.adapter = UserAdapter(this, Model.itemList)

        Log.d("bla", "bla")
    }
}

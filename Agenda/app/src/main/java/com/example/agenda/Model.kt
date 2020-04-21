package com.example.agenda

import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter

object Model {
    var itemList = ArrayList<User>()

    fun loadFile(){
        //onStart()
        TODO()
    }

    fun saveFile(){
        //onPause()
        try {
            var file: File = File("lista.txt")
            file.createNewFile()
            var stream: FileOutputStream = FileOutputStream(file)
            var writer: OutputStreamWriter = OutputStreamWriter(stream)
            for (item in Model.itemList){
                writer.write("${item.name}+\n")
                writer.write("${item.address}+\n")
                writer.write("${item.phone}+\n")
                writer.write("${item.contactType}+\n")
                writer.write("-")
            }
            writer.flush()
            writer.close()
            stream.close()
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}
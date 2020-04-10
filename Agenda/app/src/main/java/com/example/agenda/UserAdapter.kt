package com.example.agenda

import android.R
import android.content.Context
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.TwoLineListItem
import androidx.core.content.ContextCompat.getSystemService


class UserAdapter(context: Context, items: List<User>) : BaseAdapter() {
    private var context: Context = context
    private var items: List<User> = items

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return items.size;
    }

    override fun getView(position: Int, convertView: View, parent: ViewGroup): View {
        var twoLineListItem: TwoLineListItem

        if (convertView == null) {
            var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            twoLineListItem = inflater.inflate(android.R.layout.simple_list_item_2, null) as TwoLineListItem
        } else {
            twoLineListItem = convertView as TwoLineListItem
        }

        var text1: TextView = twoLineListItem.getText1()
        var text2: TextView = twoLineListItem.getText2()

        text1.setText(Model.itemList.get(position).name)
        text2.setText(Model.itemList.get(position).phone + "\n" + Model.itemList.get(position).address + "\n" + Model.itemList.get(position).contactType)

        return twoLineListItem;
    }
}
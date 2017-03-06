package com.kevicsalazar.colorpalette

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_color.view.*


/**
 * Created by Kevin.
 */

class ColorAdapter : RecyclerView.Adapter<ColorAdapter.ViewHolder>() {

    private var colors = mutableListOf<Pair<String, Int>>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            val color = colors[position]
            tvColorNum.text = color.first
            tvColorHex.text = String.format("#%06X", 0xFFFFFF and color.second)
            setBackgroundColor(color.second)
        }
    }

    override fun getItemCount() = colors.size

    fun add(colorName: String, colorInt: Int) {
        colors.add(Pair(colorName, colorInt))
        notifyItemInserted(colors.size)
    }

    fun clear() {
        colors.clear()
        notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}

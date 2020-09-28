package com.example.challenge.view.armor_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.widget.ImageViewCompat
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.challenge.R
import com.example.challenge.model.*
import java.lang.ref.WeakReference

class CraftingAdapter : RecyclerView.Adapter<CraftingAdapter.CraftingMaterialViewHolder>() {

    private lateinit var craftingData: Crafting

    init {
        setHasStableIds(true)
    }

    fun updateViewType(recyclerView: RecyclerView) {
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
    }

    fun updateData(crafting: Crafting) {
        this.craftingData = crafting
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return craftingData.materials.size
    }

    override fun getItemId(position: Int): Long {
        return craftingData.materials[position].item.id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CraftingMaterialViewHolder {
        return CraftingMaterialViewHolder(
            LayoutInflater.from(parent.context).inflate(CraftingMaterialViewHolder.VIEW_ID, null)
        )
    }

    override fun onBindViewHolder(holder: CraftingMaterialViewHolder, position: Int) {
        holder.bindView(craftingData.materials[position])
    }

    class CraftingMaterialViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            val VIEW_ID = R.layout.item_crafting_material
        }

        private val name: TextView = itemView.findViewById(R.id.name)
        private val quantity: TextView = itemView.findViewById(R.id.quantity)
        private val rarity: TextView = itemView.findViewById(R.id.rarity)
        private val limit: TextView = itemView.findViewById(R.id.limit)
        private val value: TextView = itemView.findViewById(R.id.value)
        private val description: TextView = itemView.findViewById(R.id.skill_description)

        private lateinit var material: Material

        fun bindView(material: Material) {
            this.material = material
            name.text = material.item.name
            quantity.text = "x${material.quantity}"
            rarity.text = material.item.rarity.toString()
            limit.text = material.item.carryLimit.toString()
            value.text = material.item.value.toString()
            description.text = material.item.description
        }
    }
}
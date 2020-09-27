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
import com.example.challenge.model.ArmorPiece
import com.example.challenge.model.ArmorType
import java.lang.ref.WeakReference

class ArmorListAdapter: RecyclerView.Adapter<ArmorListAdapter.ArmorPieceViewHolder>() {

    companion object {
        enum class DISPLAYTYPE {
            ROWS,
            GRID
        }
    }

    private val armorPieces: MutableList<ArmorPiece> = mutableListOf()

    val selectionUpdate = MutableLiveData<ArmorPiece>()

    init {
        setHasStableIds(true)
    }

    fun updateViewType(displayType: DISPLAYTYPE, recyclerView: RecyclerView) {
        when(displayType) {
            DISPLAYTYPE.ROWS -> {
                recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
            }
            DISPLAYTYPE.GRID -> {
                recyclerView.layoutManager = GridLayoutManager(recyclerView.context, 5, GridLayoutManager.HORIZONTAL, false)
            }
        }
    }

    fun updateData(armorList: List<ArmorPiece>) {
        armorPieces.clear()
        armorPieces.addAll(armorList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return armorPieces.size
    }

    override fun getItemId(position: Int): Long {
        return armorPieces[position].id
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmorPieceViewHolder {
        return ArmorPieceViewHolder(LayoutInflater.from(parent.context).inflate(ArmorPieceViewHolder.VIEW_ID, null), selectionUpdate)
    }

    override fun onBindViewHolder(holder: ArmorPieceViewHolder, position: Int) {
        holder.bindView(armorPieces[position])
    }

    class ArmorPieceViewHolder(itemView: View, ld: MutableLiveData<ArmorPiece>): RecyclerView.ViewHolder(itemView) {
        companion object {
            val VIEW_ID = R.layout.item_armor_piece
        }

        private val typeIcon: ImageView = itemView.findViewById(R.id.type_icon)
        private val name: TextView = itemView.findViewById(R.id.armor_name)
        private val rank: TextView = itemView.findViewById(R.id.armor_rank)
        private val rarity: TextView = itemView.findViewById(R.id.armor_rarity)
        private val baseDefense: TextView = itemView.findViewById(R.id.armor_defense_base)
        private val augDefense: TextView = itemView.findViewById(R.id.armor_defense_aug)
        private val maxDefense: TextView = itemView.findViewById(R.id.armor_defense_max)
        private val resFire: TextView = itemView.findViewById(R.id.armor_res_fire)
        private val resWater: TextView = itemView.findViewById(R.id.armor_res_water)
        private val resIce: TextView = itemView.findViewById(R.id.armor_res_ice)
        private val resThunder: TextView = itemView.findViewById(R.id.armor_res_thunder)
        private val resDragon: TextView = itemView.findViewById(R.id.armor_res_dragon)
        private val slots: TextView = itemView.findViewById(R.id.slots)
        private val slot1: RelativeLayout = itemView.findViewById(R.id.slot1)
        private val slot1Value: TextView = itemView.findViewById(R.id.slot1_text)
        private val slot2: RelativeLayout = itemView.findViewById(R.id.slot2)
        private val slot2Value: TextView = itemView.findViewById(R.id.slot2_text)
        private val slot3: RelativeLayout = itemView.findViewById(R.id.slot3)
        private val slot3Value: TextView = itemView.findViewById(R.id.slot3_text)

        private lateinit var armorPiece: ArmorPiece
        private var ldHandle: WeakReference<MutableLiveData<ArmorPiece>> = WeakReference(ld)

        init {
            itemView.setOnClickListener {
                    ldHandle.get()?.postValue(armorPiece)
            }
        }

        fun bindView(armorPiece: ArmorPiece) {
            this.armorPiece = armorPiece
            name.text = armorPiece.name
            rank.text = armorPiece.rank
            rarity.text = armorPiece.rarity.toString()

            baseDefense.text = armorPiece.defense.base.toString()
            augDefense.text = armorPiece.defense.augmented.toString()
            maxDefense.text = armorPiece.defense.max.toString()

            resFire.text = armorPiece.resistances.fire.toString()
            resWater.text = armorPiece.resistances.water.toString()
            resIce.text = armorPiece.resistances.ice.toString()
            resThunder.text = armorPiece.resistances.thunder.toString()
            resDragon.text = armorPiece.resistances.dragon.toString()

            when(armorPiece.slots.size) {
                0 -> {
                    slots.visibility = View.GONE
                    slot1.visibility = View.GONE
                    slot2.visibility = View.GONE
                    slot3.visibility = View.GONE
                }
                1 -> {
                    slots.visibility = View.VISIBLE
                    slot1.visibility = View.VISIBLE
                    slot1Value.text = armorPiece.slots[0].rank.toString()
                    slot2.visibility = View.GONE
                    slot3.visibility = View.GONE
                }
                2 -> {
                    slots.visibility = View.VISIBLE
                    slot1.visibility = View.VISIBLE
                    slot1Value.text = armorPiece.slots[0].rank.toString()
                    slot2.visibility = View.VISIBLE
                    slot2Value.text = armorPiece.slots[1].rank.toString()
                    slot3.visibility = View.GONE
                }
                3 -> {
                    slots.visibility = View.VISIBLE
                    slot1.visibility = View.VISIBLE
                    slot1Value.text = armorPiece.slots[0].rank.toString()
                    slot2.visibility = View.VISIBLE
                    slot2Value.text = armorPiece.slots[1].rank.toString()
                    slot3.visibility = View.VISIBLE
                    slot3Value.text = armorPiece.slots[2].rank.toString()
                }
            }

            val iconId = when(armorPiece.type) {
                ArmorType.HEAD.type -> R.drawable.ic_head
                ArmorType.CHEST.type -> R.drawable.ic_chest
                ArmorType.WAIST.type -> R.drawable.ic_waist
                ArmorType.GLOVES.type -> R.drawable.ic_gloves
                ArmorType.LEGS.type -> R.drawable.ic_legs
                else -> android.R.drawable.ic_menu_close_clear_cancel
            }

            Glide
                .with(itemView.context)
                .load(iconId)
                .into(typeIcon)
        }
    }
}
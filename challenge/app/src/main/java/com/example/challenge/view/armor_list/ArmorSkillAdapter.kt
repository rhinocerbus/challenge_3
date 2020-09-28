package com.example.challenge.view.armor_list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge.R
import com.example.challenge.model.ArmorSkill

class ArmorSkillAdapter : RecyclerView.Adapter<ArmorSkillAdapter.ArmorSkillViewHolder>() {

    private val armorSkills: MutableList<ArmorSkill> = mutableListOf()

    init {
        setHasStableIds(true)
    }

    fun updateViewType(recyclerView: RecyclerView) {
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, LinearLayoutManager.VERTICAL, false)
    }

    fun updateData(skillList: List<ArmorSkill>) {
        armorSkills.clear()
        armorSkills.addAll(skillList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return armorSkills.size
    }

    override fun getItemId(position: Int): Long {
        return armorSkills[position].id.toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArmorSkillViewHolder {
        return ArmorSkillViewHolder(
            LayoutInflater.from(parent.context).inflate(ArmorSkillViewHolder.VIEW_ID, null)
        )
    }

    override fun onBindViewHolder(holder: ArmorSkillViewHolder, position: Int) {
        holder.bindView(armorSkills[position])
    }

    class ArmorSkillViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        companion object {
            val VIEW_ID = R.layout.item_armor_skill
        }

        private val name: TextView = itemView.findViewById(R.id.skill_name)
        private val level: TextView = itemView.findViewById(R.id.skill_level)
        private val skill: TextView = itemView.findViewById(R.id.skill_skill)
        private val statsRow: LinearLayout = itemView.findViewById(R.id.skill_stats)
        private val modifierNames: TextView = itemView.findViewById(R.id.modifier_names)
        private val modifierValues: TextView = itemView.findViewById(R.id.modifier_values)
        private val description: TextView = itemView.findViewById(R.id.skill_description)

        private lateinit var armorSkill: ArmorSkill

        fun bindView(armorSkill: ArmorSkill) {
            this.armorSkill = armorSkill
            name.text = armorSkill.skillName
            level.text = armorSkill.level.toString()
            skill.text = armorSkill.skill.toString()

            statsRow.visibility = View.GONE
            /* expected potential multiple stats, nope - also stats appear in description, so nvm
            val modPair = armorSkill.modifiers.getActiveModierStrings()
            if(modPair != null) {
                modifierNames.text = modPair.first
                modifierValues.text = modPair.second
            } else {
                statsRow.visibility = View.GONE
            }*/
            description.text = armorSkill.description
        }
    }
}
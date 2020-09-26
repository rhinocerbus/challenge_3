package com.example.challenge.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.challenge.R
import com.example.challenge.model.ArmorPiece
import com.example.challenge.view.armor_list.ArmorListAdapter
import com.example.challenge.viewmodel.ArmorListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.serialization.ImplicitReflectionSerializer

@ImplicitReflectionSerializer
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var listLm: RecyclerView.LayoutManager
    val adapter = ArmorListAdapter()
    lateinit var viewModel : ArmorListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = recycler
        recyclerView.adapter = adapter
        adapter.updateViewType(ArmorListAdapter.Companion.DISPLAYTYPE.ROWS, recyclerView)

        initViewModels()

    }

    private fun initViewModels() {
        viewModel =  ViewModelProvider(this).get(ArmorListViewModel::class.java)
        viewModel.armorList.observe(this, Observer {
            bindArmorList(it)
        })

        viewModel.loadArmorData()
    }

    private fun bindArmorList(armor: List<ArmorPiece>) {
        adapter.updateData(armor)
    }
}

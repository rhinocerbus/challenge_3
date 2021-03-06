package com.example.challenge.view

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.challenge.R
import com.example.challenge.model.ArmorPiece
import com.example.challenge.view.armor_list.ArmorDetailsBottomSheet
import com.example.challenge.view.armor_list.ArmorListAdapter
import com.example.challenge.view.armor_list.FilterOptionsDialogSheet
import com.example.challenge.viewmodel.ArmorListViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * TODO - timeboxed:
 * - add dynamic range filtering (rarity, level, etc.)
 * - expand skill type filtering, crafting material
 * -- optimize to crawl json for filter values
 * - add sort options
 * - additional coordinator jazz (quickreturn filter bar, item details expansion vs fixed)
 * - improve landscape scaling
 * - expand details stats breakdown
 * - ...weapons!
 */

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: ArmorListViewModel
    private val adapter = ArmorListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
        initViewModels()
    }

    private fun initUI() {
        recycler.adapter = adapter
        adapter.updateViewType(ArmorListAdapter.Companion.DISPLAYTYPE.ROWS, recycler)
        adapter.selectionLiveData.observe(this, Observer {
            viewModel.updateDetailPiece(it)
            ArmorDetailsBottomSheet.show(this.supportFragmentManager)
        })

        Glide.with(this).asGif().load(R.raw.cat).into(loading_state);

        filter_text.setIconifiedByDefault(false)
        filter_text.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean { return true }

            override fun onQueryTextChange(newText: String?): Boolean {
                viewModel.updateFilterTerm(newText)
                return true
            }
        })

        filter_options.setOnClickListener {
            FilterOptionsDialogSheet.show(supportFragmentManager)
        }
    }

    private fun initViewModels() {
        viewModel = ViewModelProvider(this).get(ArmorListViewModel::class.java)
        viewModel.armorListLiveData.observe(this, Observer {
            bindArmorList(it)
        })
    }

    private fun bindArmorList(armor: List<ArmorPiece>) {
        //sometines takes a second, show the right cat
        Glide.with(this).asGif().load(R.raw.cat).into(loading_state);
        adapter.updateData(armor)

        if (armor.isEmpty()) {
            loading_state.visibility = View.VISIBLE
            if (viewModel.firstLoad) {
                Glide.with(this).asGif().load(R.raw.cat).into(loading_state);
            } else {
                Glide.with(this).asGif().load(R.raw.cat_error).into(loading_state);
            }
        } else {
            loading_state.visibility = View.GONE
        }
    }
}

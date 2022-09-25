package com.neupanesushant.rxjava_subject_application

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.neupanesushant.rxjava_subject_application.databinding.ActivityMainBinding
import com.neupanesushant.rxjava_subject_application.vm.MainViewModel
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    val items = listOf(CompletionType.ALL, CompletionType.COMPLETE, CompletionType.INCOMPLETE)
    val viewModel : MainViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val adapterItems = ArrayAdapter<String>(this, R.layout.dropdown_items, items)

        binding.dropDownAuto.setText(CompletionType.COMPLETE)
        binding.dropDownAuto.setAdapter(adapterItems)
        binding.dropDownAuto.setOnItemClickListener { adapterView, view, i, l ->
            viewModel.setChangedSelectedItemType(binding.dropDownAuto.text.toString())
        }
        binding.rv.layoutManager = LinearLayoutManager(this)

        viewModel.selectedItemList.observe(this, Observer {
            Log.i("TAG", "This size is : ${it.size}")
            val adapter = RecyclerViewAdapter(this, it)
            binding.rv.adapter = adapter
        })

        viewModel.setSelectedItemType(CompletionType.COMPLETE)

        setContentView(binding.root)
    }
}


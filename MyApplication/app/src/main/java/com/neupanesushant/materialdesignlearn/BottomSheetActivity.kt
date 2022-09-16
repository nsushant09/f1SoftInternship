package com.neupanesushant.materialdesignlearn

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neupanesushant.materialdesignlearn.databinding.ActivityBottomSheetBinding

class BottomSheetActivity : AppCompatActivity() {

    private lateinit var binding : ActivityBottomSheetBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomSheetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnShowModalSheet.setOnClickListener {
            val modalBottomSheet = ModalBottomSheet()
            modalBottomSheet.show(supportFragmentManager, ModalBottomSheet.TAG)
        }

        binding.btnShowStandardSheet.setOnClickListener {
            val standardBottomSheet = StandardBottomSheet()
            standardBottomSheet.show(supportFragmentManager, StandardBottomSheet.TAG)
        }
    }
}
package com.neupanesushant.bankingdashboardclone.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.neupanesushant.bankingdashboardclone.R
import com.neupanesushant.bankingdashboardclone.databinding.InformationBottomSheetBinding
import com.neupanesushant.bankingdashboardclone.domain.LastWeek

class InformationBottomSheet(val selectedObject : LastWeek) : BottomSheetDialogFragment() {

    private var _binding : InformationBottomSheetBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = InformationBottomSheetBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.ThemeOverlay_App_BottomSheetDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(selectedObject.image).centerCrop().into(binding.ivCompanyLogo)
        binding.tvUpdateDate.text = selectedObject.date
        binding.tvCompanyName.text = selectedObject.name

        binding.ivCancel.setOnClickListener {
            dialog?.dismiss()
        }
        val amountString = "$ ${selectedObject.price}"
        binding.tvAmount.text = amountString
        if(selectedObject.price >= 0){
            binding.tvAmount.setBackgroundResource(R.drawable.positive_price_background)
            binding.tvAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.dark_green))
        }

        binding.tvWebsite.text = selectedObject.website
    }

    companion object {
        const val TAG = "ModalBottomSheet"
    }

}
package com.neupanesushant.bankingdashboardclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neupanesushant.bankingdashboardclone.R
import com.neupanesushant.bankingdashboardclone.databinding.FragmentReportsBinding


class Reports : Fragment() {

    private var _binding  :FragmentReportsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentReportsBinding.inflate(layoutInflater)
        return binding.root
    }

}
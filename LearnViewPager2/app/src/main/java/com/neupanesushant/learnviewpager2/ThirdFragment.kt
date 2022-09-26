package com.neupanesushant.learnviewpager2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neupanesushant.learnviewpager2.databinding.FragmentThirdBinding


class ThirdFragment : Fragment() {

    private var _binding : FragmentThirdBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdBinding.inflate(layoutInflater)
        return binding.root
    }

}
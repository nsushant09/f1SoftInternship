package com.neupanesushant.learnviewpager2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neupanesushant.learnviewpager2.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {

    private var _binding : FragmentFirstBinding?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

}
package com.neupanesushant.bankingdashboardclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.neupanesushant.bankingdashboardclone.R
import com.neupanesushant.bankingdashboardclone.adapter.LastWeekDataAdapter
import com.neupanesushant.bankingdashboardclone.databinding.FragmentHomeBinding
import com.neupanesushant.bankingdashboardclone.model.LastWeek
import com.squareup.picasso.Picasso

class Home : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val adapter = LastWeekDataAdapter(requireContext(), getItems())
        binding.rvLastWeekData.layoutManager = LinearLayoutManager(requireContext())
        binding.rvLastWeekData.adapter = adapter
    }

    fun getItems() : ArrayList<LastWeek>{
        val list = ArrayList<LastWeek>()
        list.apply{
            add(LastWeek("Uber", "uber.com",
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHcAAAB3CAMAAAAO5y+4AAAAbFBMVEUAAAD////Ozs6IiIj29vZbW1uzs7O4uLiMjIyDg4ORkZEKCgrR0dHt7e35+fn8/Pynp6cRERFiYmJnZ2fj4+NycnIXFxfd3d02NjaXl5cvLy+fn5/ExMRsbGwpKSlPT09AQEAfHx9HR0d6eno7NNwZAAAC3UlEQVRoge2Xa5eyIBDHlciyRNHMS5iX9ft/x0cuoqCV1dlz9jxn/m+WZoCfDDDDOg4IBAKBQCAQCAQCgUAgEAgE+nu6BggFJ9tacut1aNAABcH5F7hHd9DBth649Tg0At74Na63yt0NDQRc4P6n3CzL4kfTxFn83tdt5SaswhgTRO9LZk8RwST3joW2FSUr2dC1PVLvUH/IdZwGha5SmF/MvjsSjT5CM2XcRyIdnQg3t59yS9eQNwtpERgukigu5v2YNH7KrdSkel35bey4T5UrUk7sT1wcfseVLNonPVOfkKt+Nyx+InpJLiyX4L3mip9DYSns6bdzyaVTC5RxlZUkFp9BdvKYd3exm+Q8cXHZdlm3gt3IDWrLF130YDyFsRVRZ5obJmvI7dzUuLdikRVv8cmjfuZqxIKLkUsfYjdxw8Zw1iKcw+QX/QFaHjddFZesbewb3MryUm48qV6t4ZL3R/1NH2M3ce0UtQ/Fkc7EETue5ip5LIjiLp4xb3IXB1IupkjddeFp3V9xF4OI4LbkC+7VXQsI+o57fs1NeEdk1zdicG+WN5NxvvEEhXt/qQ3cWlyUH9PYugbXfl73kTjk5/VgCL3knsXSmGmkJhdZY0Ti6NVlLT/jSgY2QqmS65SvfGOESBdurcJiJoe4m03xlJuIYmX8LyILy4yL53N3RB/FfBENX70KXnNl1GYltVHYeV0g1ykY4taSVrTFN1dT0fD5nsXbuIWszikTU/3QsXIadTDy5JIz5VbFQB6EVO1DW4nif9zGde6aRManUmRz+ewe9cZQ6EIz+itKD2oakc23cGXumAuZ+eoUWv6pvnXeYmy8mesUaD4SM4cZXKcxMnG+n4/dGT6yk9Zt3GH09Cz0GrVvfJ9EYOPhiaxzYlpa6atm2kfkGXFUGnxWj7TiO0VBxZpH/tr3AnTw7ZwpfcOD7+D5T8o8CAQCgUAgEAgEAoFAIBAIBPpz+gc4LSQwFWFHTgAAAABJRU5ErkJggg==",
                "30, August", -22.12))
            add(LastWeek("Ebay", "ebay.com",
                "https://pool.img.aptoide.com/vagalexstore/b93c9563e43e31d203c5fe7db099b066.png",
                "30, August", 1232.32))
            add(LastWeek("Pure Gym", "puregym.com",
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHcAAAB3CAMAAAAO5y+4AAAAaVBMVEX///8AAADl5eX29vb5+fm8vLzs7OzKyspOTk7g4ODY2Nj8/PySkpKMjIyenp6bm5saGhrQ0NBcXFx1dXWnp6dtbW07Ozuurq7CwsIyMjJoaGh8fHxTU1MVFRUsLCwjIyNHR0eEhIQNDQ20t47jAAACCElEQVRoge3XyZaDIBAF0C6N4DzHKWpM/v8jWzO0RhHB4KLPqbsN+CJQCD8/CCGEEEIIIYQQQgj9O9phT9Z1zo8GPSZUK1Kbk0trR32mbrvVOeK1sKFQHUryJASPP3sEypPKTId6GUCbb41hC4G6UNuPoRdvL5i+HVGTqeVneLgJlEcAYCrIJIXXPkOvPq9y3mjf0v0yU4/8LnyGgmkIrRUy/MmvitcyqhLeqkiwIKuh9e7VTOjN/MuE8GKJdswfHXZl6lYQX8dQKAPx1ak1Q49MPpQYlxKmOrFpfXmUmnQu9Uz4JFCtU8Wz112ii5VWMCdSrR/e0yO4BvvBXWRC6Et/UtJ3X2O7rU7dbBkKtUDXOWecno2Gdl6HjFA42/Kpk9flvrBmJKwX7Qd44yu3qpk8hP3HSeSZDTMUsnTvt4ROH3NffPj1fuW27MxhExbZ+tmS+VyN69LpVy57cB8q7uFly3LobnlkW7YRrEzoy95pfaG8Z69q5Kt1xt2Raio4/iXbMTOJ8FeOJ5YLvbtqzmBOLZPapfvrZkbifeNC4fFadH6bZNcmvCoQSg1d1RdFSyC1zZXeX564m9LAPOZuurFhiZ9JZS0PSiNXWd0wrJVwvXmD/NKNEdq5hw3wKPoc62vsWwesYBYrr7qsbLPOTFJb0TVY1ImQI5cRQgghhBBCCCGEEELifgEUhRRaGyMaJgAAAABJRU5ErkJggg==",
                "28, August", -82.99))
            add(LastWeek("Amazon", "amazon.com",
                "data:image/png;base64,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",
                "27, August", -112.50))
            add(LastWeek("Nike", "nike.com",
                "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAHcAAAB3CAMAAAAO5y+4AAAAaVBMVEX///8AAADl5eX29vb5+fm8vLzs7OzKyspOTk7g4ODY2Nj8/PySkpKMjIyenp6bm5saGhrQ0NBcXFx1dXWnp6dtbW07Ozuurq7CwsIyMjJoaGh8fHxTU1MVFRUsLCwjIyNHR0eEhIQNDQ20t47jAAACCElEQVRoge3XyZaDIBAF0C6N4DzHKWpM/v8jWzO0RhHB4KLPqbsN+CJQCD8/CCGEEEIIIYQQQgj9O9phT9Z1zo8GPSZUK1Kbk0trR32mbrvVOeK1sKFQHUryJASPP3sEypPKTId6GUCbb41hC4G6UNuPoRdvL5i+HVGTqeVneLgJlEcAYCrIJIXXPkOvPq9y3mjf0v0yU4/8LnyGgmkIrRUy/MmvitcyqhLeqkiwIKuh9e7VTOjN/MuE8GKJdswfHXZl6lYQX8dQKAPx1ak1Q49MPpQYlxKmOrFpfXmUmnQu9Uz4JFCtU8Wz112ii5VWMCdSrR/e0yO4BvvBXWRC6Et/UtJ3X2O7rU7dbBkKtUDXOWecno2Gdl6HjFA42/Kpk9flvrBmJKwX7Qd44yu3qpk8hP3HSeSZDTMUsnTvt4ROH3NffPj1fuW27MxhExbZ+tmS+VyN69LpVy57cB8q7uFly3LobnlkW7YRrEzoy95pfaG8Z69q5Kt1xt2Raio4/iXbMTOJ8FeOJ5YLvbtqzmBOLZPapfvrZkbifeNC4fFadH6bZNcmvCoQSg1d1RdFSyC1zZXeX564m9LAPOZuurFhiZ9JZS0PSiNXWd0wrJVwvXmD/NKNEdq5hw3wKPoc62vsWwesYBYrr7qsbLPOTFJb0TVY1ImQI5cRQgghhBBCCCGEEELifgEUhRRaGyMaJgAAAABJRU5ErkJggg==",
                "27, August", -99.00))
        }
        return list
    }
}
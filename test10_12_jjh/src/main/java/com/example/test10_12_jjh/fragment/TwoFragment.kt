package com.example.test10_12_jjh.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.test10_12_jjh.R
import com.example.test10_12_jjh.databinding.ActivityFragTestBinding
import com.example.test10_12_jjh.databinding.FragmentTwoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [TwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TwoFragment : Fragment() {
    lateinit var binding : FragmentTwoBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_two, container, false)
        binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }
}
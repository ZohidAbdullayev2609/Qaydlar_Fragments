package com.example.qaydlarfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.qaydlarfragments.R
import com.example.qaydlarfragments.databinding.FragmentSecondBinding
import com.example.qaydlarfragments.model.User
import com.example.qaydlarfragments.utils.MySharedPrefernce
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding
    private lateinit var list: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): ConstraintLayout {

        MySharedPrefernce.init(requireContext())

        _binding = FragmentSecondBinding.inflate(inflater, container, false)

        binding?.imageBack?.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, FirstFragment())?.commit()
        }

        list = ArrayList()
        binding?.imageCheck?.setOnClickListener {
            if (binding?.editTitle?.text.toString() != "" &&
                binding?.editDescription?.text.toString() != ""
            ) {
                val title = binding?.editTitle?.text.toString()
                val description = binding?.editDescription?.text.toString()

                val gson = Gson()
                val type = object : TypeToken<ArrayList<User>>() {}.type
                val userData = MySharedPrefernce.userData
                if (userData != null) {
                    list = gson.fromJson(userData, type)
                }
                list.add(User(title, description))
                val sNew = gson.toJson(list)
                MySharedPrefernce.userData = sNew

                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.container, FirstFragment())?.commit()
            } else {
                Toast.makeText(requireContext(), "Ma'lumotlarni to'ldiring", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val images = AnimationUtils.loadAnimation(requireContext(), R.anim.images)
        val texts = AnimationUtils.loadAnimation(requireContext(), R.anim.texts)

        binding?.imageBack?.startAnimation(images)
        binding?.imageCheck?.startAnimation(images)
//        binding?.imageMenu?.startAnimation(images)

        binding?.editTitle?.startAnimation(texts)
        binding?.editDescription?.startAnimation(texts)

        binding?.editTitle?.requestFocus()
        binding?.editDescription?.setBackgroundResource(android.R.color.transparent)

        return _binding?.root!!
    }
}

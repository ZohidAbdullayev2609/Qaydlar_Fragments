package com.example.qaydlarfragments.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.qaydlarfragments.R
import com.example.qaydlarfragments.databinding.FragmentThirdBinding
import com.example.qaydlarfragments.model.User
import com.example.qaydlarfragments.utils.MySharedPrefernce
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ThirdFragment : Fragment() {

    private var _binding: FragmentThirdBinding? = null
    private val binding get() = _binding

    var pos = -1
    private lateinit var list: ArrayList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): ConstraintLayout {

        _binding = FragmentThirdBinding.inflate(inflater, container, false)

        MySharedPrefernce.init(requireContext())

        list = ArrayList()
        val position = arguments?.getInt("position")
        pos = position!!
        val gson = Gson()
        val type = object : TypeToken<ArrayList<User>>() {}.type
        val user = MySharedPrefernce.userData
        list = gson.fromJson(user, type)

        binding?.editTitle2?.setText(list[position].title)
        binding?.editDescription2?.setText(list[position].description)

        binding?.imageCheck2?.setOnClickListener {

            val title = binding?.editTitle2?.text.toString()
            val description = binding?.editDescription2?.text.toString()

            if (title != "" && description != "") {
                list[pos].title = title
                list[pos].description = description
                val gson = Gson()

                val sNew1 = gson.toJson(list)
                MySharedPrefernce.userData = sNew1

                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.container, FirstFragment())?.commit()

            } else {
                Toast.makeText(requireContext(), "Ma'lumotlarni to'ldiring", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        binding?.imageDelete2?.setOnClickListener {
            val dialog = AlertDialog.Builder(requireContext()).create()
            dialog.setTitle("Ma'lumotlarni o'chirishni xohlaysizmi?")
            dialog.setMessage("Ma'lumotlarni qayta tiklab bo'lmaydi!")

            dialog.setButton(AlertDialog.BUTTON_POSITIVE, "yes") { _, _ ->
                val position = arguments?.getInt("position")
                pos = position!!
                list = ArrayList()
                val gson = Gson()
                val type = object : TypeToken<ArrayList<User>>() {}.type
                val user = MySharedPrefernce.userData
                list = gson.fromJson(user, type)
                list.removeAt(position)

                val sNew1 = gson.toJson(list, type)
                MySharedPrefernce.userData = sNew1

                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.container, FirstFragment())?.commit()
            }
            dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "no") { _, _ ->
            }
            dialog.show()
        }

        binding?.imageBack2?.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, FirstFragment())?.commit()
        }

        binding?.editDescription2?.setBackgroundResource(android.R.color.transparent)

        return _binding?.root!!
    }

}


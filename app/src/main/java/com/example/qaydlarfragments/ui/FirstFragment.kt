package com.example.qaydlarfragments.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.qaydlarfragments.R
import com.example.qaydlarfragments.adapter.MyRecycleAdapter
import com.example.qaydlarfragments.databinding.FragmentFirstBinding
import com.example.qaydlarfragments.model.User
import com.example.qaydlarfragments.utils.MySharedPrefernce
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.*

class FirstFragment : Fragment(), MyRecycleAdapter.OnClick {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding
    private lateinit var list: ArrayList<User>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): ConstraintLayout? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        MySharedPrefernce.init(requireContext())

        list = ArrayList()

        val gson = Gson()
        val type = object : TypeToken<ArrayList<User>>() {}.type
        val userData = MySharedPrefernce.userData
        if (userData == null) {
            Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
        } else {
            list = gson.fromJson(userData, type)
            binding?.rv?.adapter = MyRecycleAdapter(list, this)
        }

        binding?.cardAdd?.setOnClickListener {
            val transaction = fragmentManager?.beginTransaction()
            transaction?.replace(R.id.container, SecondFragment())?.commit()
        }
        return binding?.root!!
    }

    override fun onClickListener(position: Int) {
        val thirdFragment = ThirdFragment()
        val bundle = Bundle()
        bundle.putInt("position", position)
        thirdFragment.arguments = bundle
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.container, thirdFragment)?.commit()
    }

}


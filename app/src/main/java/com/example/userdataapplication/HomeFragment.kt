package com.example.userdataapplication

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(),View.OnClickListener {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.add_user.setOnClickListener(this)
        view.list_user.setOnClickListener(this)
        view.update_user.setOnClickListener(this)
        view.delete_all_users.setOnClickListener(this)
        return view
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.add_user -> findNavController().navigate(R.id.action_homeFragment_to_addFragment)
            R.id.list_user, R.id.update_user ->
                findNavController().navigate(R.id.action_homeFragment_to_ListFragment)
            R.id.delete_all_users ->
                findNavController().navigate(R.id.action_homeFragment_to_ListFragment)
            else -> Toast.makeText(
                requireContext(),
                "Click on any one task",
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}
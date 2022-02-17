package com.example.userdataapplication

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userdataapplication.UserData.UserViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.fragment_list.view.*

class ListFragment : Fragment() {
    private lateinit var mviewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_list, container, false)

        //To display data in RecyclerView
        val adapter = ListAdapter(requireContext())
        val recyclerView = view.recycler_view
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mviewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mviewModel.readAllUsers.observe(viewLifecycleOwner, Observer {
            user -> adapter.setData(user)
        })

        val deleteButton = view.findViewById<FloatingActionButton>(R.id.deleteActionButton)
        deleteButton.visibility= View.VISIBLE
        deleteButton.setOnClickListener {
            deleteAllUsers()
        }
        return view
    }

    private fun deleteAllUsers() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setPositiveButton("yes"){_,_ ->
                mviewModel.deleteAllUsers()
            Toast.makeText(requireContext(),"successfully removes all users",Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_ListFragment_to_homeFragment)
        }
        builder.setNegativeButton("No"){_,_ ->
        }
        builder.setTitle("Delete Everything")
        builder.setMessage("Are you sure you want to delete everything?")
        builder.create().show()

    }
}
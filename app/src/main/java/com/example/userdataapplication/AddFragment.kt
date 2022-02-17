package com.example.userdataapplication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.userdataapplication.UserData.User
import com.example.userdataapplication.UserData.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mViewModel : UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)


       // mViewModel = ViewModelProvider(this,ViewModelProvider.AndroidViewModelFactory
          //  .getInstance(requireActivity().application))[UserViewModel::class.java]
        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_button.setOnClickListener {

            insertDataToDatabase()
        }
        view.show_list.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_ListFragment)
        }
        return view

    }

    private fun insertDataToDatabase() {

        val addFirstName = add_firstName.text.toString()
        val addLastName = add_lastName.text.toString()
        val addAge = add_age.text
        val addcountry = add_country.text.toString()
        if(!(TextUtils.isEmpty(addFirstName) ||TextUtils.isEmpty(addLastName)||
            addAge.isEmpty()||
            TextUtils.isEmpty(addcountry))){

                //creating user object table
                val user = User(0,addFirstName,addLastName,Integer.parseInt(addAge.toString()),addcountry)
                //adding user data to the database table
                mViewModel.addUser(user)
                Toast.makeText(requireContext(),"user Details saved successfully",Toast.LENGTH_SHORT).show()

        }
        else{
            Toast.makeText(requireContext(),"enter the details correctly or missing some fields",Toast.LENGTH_SHORT).show()
        }

    }


}
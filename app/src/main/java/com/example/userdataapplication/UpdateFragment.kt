package com.example.userdataapplication

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.userdataapplication.UserData.User
import com.example.userdataapplication.UserData.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*

class UpdateFragment : Fragment() {

    private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mViewModel:UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        view.update_firstName.setText(args.currentUser.FirstName)
        view.update_LastName.setText(args.currentUser.LastName)
        view.update_age.setText(args.currentUser.Age.toString())
        view.update_country.setText(args.currentUser.Country)

        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.update_button.setOnClickListener {
            updateItem()
        }
        return view
    }

    private fun updateItem() {
        val firstname = update_firstName.text
        val lastname = update_LastName.text
        val age = update_age.text
        val country = update_country.text

        if(!(TextUtils.isEmpty(firstname) || TextUtils.isEmpty(lastname)||
                    age.isEmpty()||
                    TextUtils.isEmpty(country))){

            //creating user object table
            val user = User(args.currentUser.id,firstname.toString(),lastname.toString(),
                Integer.parseInt(update_age.text.toString()),country.toString())
            //adding user data to the database table
            mViewModel.updateUser(user)
            Toast.makeText(requireContext(),"user Details updated successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_updateFragment_to_HomeFragment)
        }
        else{
            Toast.makeText(requireContext(),"enter the details correctly or missing some fields",
                Toast.LENGTH_SHORT).show()
        }

    }
}
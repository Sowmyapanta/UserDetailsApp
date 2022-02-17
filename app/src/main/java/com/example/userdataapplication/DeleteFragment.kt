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
import kotlinx.android.synthetic.main.fragment_delete.view.*
import kotlinx.android.synthetic.main.fragment_update.*


class DeleteFragment : Fragment() {

    private val args by navArgs<DeleteFragmentArgs>()
    private lateinit var mViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_delete, container, false)

        view.delete_firstName.setText(args.currentDeleteUser.FirstName)
        view.delete_LastName.setText(args.currentDeleteUser.LastName)
        view.delete_age.setText(args.currentDeleteUser.Age.toString())
        view.delete_country.setText(args.currentDeleteUser.Country)

        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.delete_button.setOnClickListener {
            mViewModel.deleteUser(args.currentDeleteUser)
            Toast.makeText(requireContext(),"Deleted successfully",Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_deleteFragment_to_homeFragment)
        }
        return view
    }
}

package com.example.userdataapplication

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.userdataapplication.UserData.User
import com.example.userdataapplication.UserData.UserViewModel
import kotlinx.android.synthetic.main.list_view.view.*
import kotlin.coroutines.coroutineContext

class ListAdapter(val context: Context) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private lateinit var mviewModel : UserViewModel

    private var userList = emptyList<User>()

    class MyViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
       return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_view,parent,false))
    }

    override fun onBindViewHolder(holder: ListAdapter.MyViewHolder, position: Int) {
        val currentItem = userList[position]
        holder.itemView.id_txt.text = currentItem.id.toString()
        holder.itemView.firstName_txt.text = currentItem.FirstName
        holder.itemView.LastName_txt.text = currentItem.LastName
        holder.itemView.Age_txt.text = currentItem.Age.toString()
        holder.itemView.Country_txt.text = currentItem.Country

        holder.itemView.row_layout.setOnClickListener {

            val builder = AlertDialog.Builder(context)
            builder.setPositiveButton("Update"){_,_ ->
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
            builder.setNegativeButton("Delete"){_,_ ->
                val action = ListFragmentDirections.actionListFragmentToDeleteFragment(currentItem)
                holder.itemView.findNavController().navigate(action)
            }
            builder.setTitle("Update/Delete")
            builder.setMessage("which Action you wnat to perform")
            builder.create().show()


        }
    }
    fun setData(user:List<User>){
        this.userList = user
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return userList.size
    }
}
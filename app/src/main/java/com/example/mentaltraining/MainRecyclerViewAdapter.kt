package com.example.mentaltraining

import android.content.ContentValues.TAG
import android.content.DialogInterface.OnClickListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/*
    Adapter class for all the recycler view containers in the main menu lists.
    It takes Array<String> and binds values,onClickListeners,etc to each item in recycler view
 */
class MainRecyclerViewAdapter (private val categoryList : Array<String>, onRecyclerItemClick : OnRecyclerItemClick) : RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder>() {

    private val mOnItemClick : OnRecyclerItemClick = onRecyclerItemClick
    private val mCategoryList : Array<String> = categoryList

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):  ViewHolder {
        //
        val v = LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return ViewHolder(v, mOnItemClick)
    }

    override fun getItemCount(): Int {
        return mCategoryList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(mCategoryList[position])
    }

    /*
        sets up all the individual items in recyclerView
     */
    class ViewHolder(itemView : View, onRecyclerItemClick: OnRecyclerItemClick) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val onItemClick : OnRecyclerItemClick
        init {
            itemView.setOnClickListener(this)
            this.onItemClick = onRecyclerItemClick
        }

        //sets the value for all the list items
        fun bindItems(category : String) {
            val categoryText = itemView.findViewById(R.id.categoryText) as TextView
            categoryText.text = category
        }

        //set onClick listener
        override fun onClick(v: View?) {
            onItemClick.onItemClick(adapterPosition)
        }
    }

    /*
        -Interface that passes the onClickListener back to the implementer
     */
    interface OnRecyclerItemClick{
        fun onItemClick(position:Int)
    }

}
package com.iav.contestdataprovider.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iav.contestdataprovider.data.model.RandomString
import com.iav.contestdataprovider.databinding.ItemStringBinding

//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.DiffUtil
//import androidx.recyclerview.widget.ListAdapter
//import androidx.recyclerview.widget.RecyclerView
//import com.iav.contestdataprovider.data.model.RandomString
//import com.iav.contestdataprovider.databinding.ItemStringBinding
//
//class StringListAdapter(private val onDelete: (RandomString) -> Unit) :
//    ListAdapter<RandomString, StringListAdapter.StringViewHolder>(DiffCallback()) {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StringViewHolder {
//        val binding = ItemStringBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//        return StringViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: StringViewHolder, position: Int) {
//        holder.bind(getItem(position), onDelete)
//    }
//
//    class StringViewHolder(private val binding: ItemStringBinding) : RecyclerView.ViewHolder(binding.root) {
//        fun bind(randomString: RandomString, onDelete: (RandomString) -> Unit) {
//            binding.stringTextView.text = randomString.value
//            binding.lengthTextView.text = "Length: ${randomString.length}"
//            binding.dateTextView.text = "Created: ${randomString.created}"
//            binding.deleteButton.setOnClickListener {
//                onDelete(randomString)
//            }
//        }
//    }
//
//    class DiffCallback : DiffUtil.ItemCallback<RandomString>() {
//        override fun areItemsTheSame(oldItem: RandomString, newItem: RandomString) = oldItem.id == newItem.id
//        override fun areContentsTheSame(oldItem: RandomString, newItem: RandomString) = oldItem == newItem
//    }
//}

class RandomStringAdapter(
    private val onDelete: (Int) -> Unit
) : RecyclerView.Adapter<RandomStringAdapter.ViewHolder>() {

    private val data = mutableListOf<RandomString?>()

    fun submitList(newData: List<RandomString?>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemStringBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        data[position]?.let { holder.bind(it) }
        holder.binding.deleteButton.setOnClickListener {
            onDelete(position)
        }
    }

    override fun getItemCount() = data.size

    class ViewHolder(val binding: ItemStringBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(randomString: RandomString) {
            binding.stringTextView.text = randomString.value
            binding.lengthTextView.text = "Length: ${randomString.length}"
            binding.dateTextView.text = "Created: ${randomString.created}"
        }
    }
}

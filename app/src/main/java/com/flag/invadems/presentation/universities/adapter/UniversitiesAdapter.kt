package com.flag.invadems.presentation.universities.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.flag.invadems.R
import com.flag.module.data.models.University
import com.flag.invadems.presentation.universities.UniversitiesViewModel

class UniversitiesAdapter(var viewModel: UniversitiesViewModel

) :
    RecyclerView.Adapter<UniversitiesAdapter.UniversityViewHolder>() {

    private var universities: MutableList<University> = mutableListOf()
    private var itemClickListener: ((University) -> Unit)? = null

    fun setOnItemClickListener(listener: (University) -> Unit) {
        itemClickListener = listener
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UniversityViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_university_item, parent, false)
        return UniversityViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UniversityViewHolder, position: Int) {
        val university = universities[position]
        holder.bind(university)

    }

    override fun getItemCount(): Int {
        return universities.size
    }

    fun updateData(newData: MutableList<University>) {
        this.universities = newData
//        universities.clear()  // Clear existing data
//        universities.addAll(newData)
        Log.d("Unveristy_adapter",universities.toString())
        notifyDataSetChanged()
    }

   inner  class UniversityViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textUniversityName: TextView = itemView.findViewById(R.id.textUniversityName)
        private val textUniversityState: TextView = itemView.findViewById(R.id.textUniversityState)

        fun bind(university: University) {
            textUniversityName.text = university.name
            textUniversityState.text = university.stateProvince

            itemView.setOnClickListener {
                viewModel.onUniversityItemClick(university)
            }

        }
    }
}

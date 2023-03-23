package com.example.countriesappxml.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.countriesappxml.data.remote.dto.CountryDto
import com.example.countriesappxml.databinding.RcvRowItemBinding

class CountryAdapter(
    private val countryList :ArrayList<CountryDto>,
    private val mContext: Context
    ) : RecyclerView.Adapter<CountryAdapter.MyViewHolder>() {

    class MyViewHolder(val binding: RcvRowItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            RcvRowItemBinding.inflate(
                LayoutInflater.from(mContext),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.binding.countryName.text = countryList[position].name.common
        holder.binding.region.text = countryList[position].region
        Glide.with(mContext).load(countryList[position].flags.png).into(holder.binding.flag)
    }

    override fun getItemCount() = countryList.size



}
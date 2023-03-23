package com.example.countriesappxml.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countriesappxml.R
import com.example.countriesappxml.common.Resource
import com.example.countriesappxml.data.remote.dto.CountryDto
import com.example.countriesappxml.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private val mainActivityViewModel : MainActivityViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        lifecycleScope.launchWhenCreated {
            mainActivityViewModel.countryList.collect{
                when(it){
                    is Resource.Error -> {
                        binding.rcv.visibility = View.GONE
                        binding.errorText.apply {
                            visibility = View.VISIBLE
                            text = it.message
                        }
                    }
                    is Resource.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility = View.GONE
                        setData(it.data)
                    }
                }
            }
        }
    }

    private fun setData(data: List<CountryDto>?) {

        data?.let {
            it.forEach {country->

                Log.d("burdayım", "country: $country")
            }
        }

        binding.rcv.apply {
            adapter = CountryAdapter(data as ArrayList<CountryDto>, this@MainActivity)
            layoutManager = LinearLayoutManager(this@MainActivity)
        }

    }
}
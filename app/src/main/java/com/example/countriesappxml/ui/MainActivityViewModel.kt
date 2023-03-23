package com.example.countriesappxml.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countriesappxml.common.Resource
import com.example.countriesappxml.data.remote.dto.CountryDto
import com.example.countriesappxml.domain.use_cases.GetCountriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase
) : ViewModel(){

    private val _countryList: MutableStateFlow<Resource<List<CountryDto>>> = MutableStateFlow(Resource.Loading())
    val countryList = _countryList.asStateFlow()


    init {
        getCountries()
    }

    private fun getCountries() {
        viewModelScope.launch {
            getCountriesUseCase().collect{
                when(it){
                    is Resource.Error -> {
                        it.message?.let {error->
                            _countryList.value = Resource.Error(error)
                        }
                    }
                    is Resource.Loading -> _countryList.value = Resource.Loading()
                    is Resource.Success -> {
                        it.data?.let { list ->
                            _countryList.value = Resource.Success(list)

                        }
                    }
                }

            }
        }
    }
}
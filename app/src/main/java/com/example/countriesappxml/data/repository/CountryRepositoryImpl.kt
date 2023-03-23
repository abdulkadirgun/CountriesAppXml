package com.example.countriesappxml.data.repository

import com.example.countriesappxml.data.remote.CountryApi
import com.example.countriesappxml.data.remote.dto.CountryDto
import com.example.countriesappxml.domain.repository.CountryRepository
import javax.inject.Inject

class CountryRepositoryImpl @Inject constructor(
    private val api: CountryApi
) :CountryRepository{
    override suspend fun getCountries(): List<CountryDto> {
        return api.getCountries()
    }
}
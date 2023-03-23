package com.example.countriesappxml.domain.repository

import com.example.countriesappxml.data.remote.dto.CountryDto

interface CountryRepository {

    suspend fun getCountries(): List<CountryDto>
}
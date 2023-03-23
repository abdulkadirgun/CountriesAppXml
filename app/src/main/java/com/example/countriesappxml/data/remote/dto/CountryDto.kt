package com.example.countriesappxml.data.remote.dto

data class CountryDto(
    val capital: List<String>,
    val continents: List<String>,
    val currencies: Currencies,
    val flags: Flags,
    val languages: Languages,
    val maps: Maps,
    val name: Name,
    val population: Int,
    val region: String,
    val subregion: String,
    val unMember: Boolean
)
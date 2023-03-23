package com.example.countriesappxml.domain.use_cases

import com.example.countriesappxml.common.Resource
import com.example.countriesappxml.data.remote.dto.CountryDto
import com.example.countriesappxml.domain.repository.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCountriesUseCase @Inject constructor(
    private val countryRepository: CountryRepository
){
    operator fun invoke() : Flow<Resource<List<CountryDto>>> {
        return flow {

            try {
                emit(Resource.Loading())

                emit(Resource.Success(countryRepository.getCountries()))
            }
            catch (e: Exception){
                emit(Resource.Error(e.localizedMessage ?: "an error was occurred"))
            }


        }

    }
}
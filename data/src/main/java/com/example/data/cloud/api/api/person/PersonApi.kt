package com.example.data.cloud.api.api.person

import com.example.data.cloud.api.utils.Endpoints.Person.PERSON_DETAILS
import com.example.data.cloud.api.utils.Endpoints.Person.PERSON_POPULAR
import com.example.data.cloud.api.utils.Utils
import com.example.data.cloud.models.person.PersonDetailsCloud
import com.example.data.cloud.models.person.PersonsCloud
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonApi {
    @GET(PERSON_POPULAR)
    suspend fun getPersons(
        @Query("api_key") api_key: String = Utils.API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") page: Int,
    ): Response<PersonsCloud>

    @GET(PERSON_DETAILS)
    suspend fun getPersonDetails(
        @Path("person_id") id: Int,
        @Query("api_key") api_key: String = Utils.API_KEY,
        @Query("language") language: String = "ru",
    ): Response<PersonDetailsCloud>
}
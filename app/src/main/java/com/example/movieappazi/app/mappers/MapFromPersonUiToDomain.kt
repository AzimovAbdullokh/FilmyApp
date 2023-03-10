package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.person.PersonDomain
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.person.PersonUi

class MapFromPersonUiToDomain(
    private val mapFromListMovieUiToDomain: BaseMapper<List<MovieUi>, List<MovieDomain>>,
) : BaseMapper<PersonUi, PersonDomain> {
    override fun map(from: PersonUi) = from.run {
        PersonDomain(
            profile_path = profile_path,
            adult = adult,
            id = id,
            known_for = mapFromListMovieUiToDomain.map(known_for),
            name = name,
            popularity = popularity)
    }
}
package com.example.data.data.mappers

import com.example.data.data.models.movie.MovieData
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.MovieDomain
import javax.inject.Inject

class MapFromMovieDataToMovieDomain @Inject constructor() : BaseMapper<MovieData, MovieDomain> {
    override fun map(from: MovieData) = from.run {
        MovieDomain(
            posterPath = posterPath,
            adult = adult,
            overview = overview,
            releaseDate = releaseDate,
            id = id,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            title = title,
            backdropPath = backdropPath,
            popularity = popularity,
            voteCount = voteCount,
            video = video,
            rating = rating,
            genre_ids = genre_ids?.map { id -> id },
        )
    }
}
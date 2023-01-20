package com.example.data.dataMappers

import com.example.data.dataModel.person.PersonDetailsData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDetailsDomain

class MapFromPersonDetailsDataToDomain : BaseMapper<PersonDetailsData, PersonDetailsDomain> {
    override fun map(from: PersonDetailsData) = from.run {
        PersonDetailsDomain(
            known_for_department = known_for_department,
            also_known_as = also_known_as,
            biography = biography,
            birthday = birthday,
            deathDay = deathDay,
            gender = gender,
            id = id,
            name = name,
            popularity = popularity,
            place_of_birth = place_of_birth,
            profile_path = profile_path,
        )
    }
}
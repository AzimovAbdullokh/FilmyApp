package com.example.movieappazi.uiMappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDomain
import com.example.movieappazi.uiModels.person.PersonUi

class MapFromListPersonDomainToUi(
    private val mapFromPersonDomainToUi: BaseMapper<PersonDomain, PersonUi>,
) : BaseMapper<List<PersonDomain>, List<PersonUi>> {
    override fun map(from: List<PersonDomain>) = from.map {
        mapFromPersonDomainToUi.map(it)
    }
}
package com.codewithfk.data.mappers

import com.codewithfk.data.model.ProgrammeDto
import com.codewithfk.domain.model.Programme

object ProgrammeMapper {
    fun toDomain(dto: ProgrammeDto): Programme {
        return Programme(
            id = dto.id,
            title = dto.intitule ?: "Programme ${dto.id}",
            startDate = dto.debut,
            endDate = dto.fin,
            typeMarche = dto.typeMarche
        )
    }

    fun toDomain(dtos: List<ProgrammeDto>): List<Programme> {
        return dtos.map { toDomain(it) }
    }
}

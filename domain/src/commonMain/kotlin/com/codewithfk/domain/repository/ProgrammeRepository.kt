package com.codewithfk.domain.repository

import com.codewithfk.domain.model.Programme

interface ProgrammeRepository {
    suspend fun getAllProgrammes(token: String): Result<List<Programme>>
}

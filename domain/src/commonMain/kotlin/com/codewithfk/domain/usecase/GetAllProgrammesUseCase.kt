package com.codewithfk.domain.usecase

import com.codewithfk.domain.model.Programme
import com.codewithfk.domain.repository.ProgrammeRepository

class GetAllProgrammesUseCase(private val repository: ProgrammeRepository) {
    suspend fun execute(token: String): Result<List<Programme>> {
        return repository.getAllProgrammes(token)
    }
}

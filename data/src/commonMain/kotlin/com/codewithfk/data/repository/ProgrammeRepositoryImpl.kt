package com.codewithfk.data.repository

import com.codewithfk.data.datasource.RemoteDataSource
import com.codewithfk.data.mappers.ProgrammeMapper
import com.codewithfk.domain.model.Programme
import com.codewithfk.domain.repository.ProgrammeRepository

class ProgrammeRepositoryImpl(private val dataSource: RemoteDataSource) : ProgrammeRepository {
    override suspend fun getAllProgrammes(token: String): Result<List<Programme>> {
        return try {
            val response = dataSource.getProgrammes(token)
            if (response.isSuccess) {
                val dtos = response.getOrNull().orEmpty()
                Result.success(ProgrammeMapper.toDomain(dtos))
            } else {
                val exception = response.exceptionOrNull()
                Result.failure(exception ?: Exception("Failed to load programmes."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

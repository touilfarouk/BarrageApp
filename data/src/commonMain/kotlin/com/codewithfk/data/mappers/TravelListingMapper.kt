package com.codewithfk.data.mappers

import com.codewithfk.data.model.TravelListingDto
import com.codewithfk.domain.model.TravelListing

object TravelListingMapper {

    fun toDomain(dto: TravelListingDto): TravelListing {
        return TravelListing(
            id = dto.id,
            title = dto.title,
            description = dto.description,
            location = dto.location,
            imageUrl = dto.imageUrl,
            pricePerNight = dto.pricePerNight,
            rating = dto.rating,
            amenities = dto.amenities ?: emptyList(),
            hostName = dto.hostName,
            isFavorite = dto.isFavorite
        )
    }

    fun toDomain(dtos: List<TravelListingDto>): List<TravelListing> {
        return dtos.map { toDomain(it) }
    }

    fun toDto(domain: TravelListing): TravelListingDto {
        return TravelListingDto(
            id = domain.id,
            title = domain.title,
            description = domain.description,
            location = domain.location,
            imageUrl = domain.imageUrl,
            pricePerNight = domain.pricePerNight,
            rating = domain.rating,
            amenities = domain.amenities.takeIf { it.isNotEmpty() },
            hostName = domain.hostName,
            isFavorite = domain.isFavorite
        )
    }
}
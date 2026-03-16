package com.codewithfk.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProgrammeDto(
    @SerialName("IDProgramme")
    val id: Int,
    val intitule: String? = null,
    val debut: String? = null,
    val fin: String? = null,
    @SerialName("type_marche")
    val typeMarche: String? = null
)

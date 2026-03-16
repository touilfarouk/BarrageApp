package com.codewithfk.domain.model

data class Programme(
    val id: Int,
    val title: String,
    val startDate: String?,
    val endDate: String?,
    val typeMarche: String?
)

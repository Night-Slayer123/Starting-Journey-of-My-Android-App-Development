package com.driven.youtubeclone.Model

data class RegionRestriction(
    val allowed: List<String>,
    val blocked: List<String>
)
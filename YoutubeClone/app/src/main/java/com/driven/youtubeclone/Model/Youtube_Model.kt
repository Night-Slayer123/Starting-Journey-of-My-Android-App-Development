package com.driven.youtubeclone.Model

data class Youtube_Model(
    val etag: String,
    val items: List<Item>,
    val kind: String,
    val nextPageToken: String,
    val pageInfo: PageInfo
)
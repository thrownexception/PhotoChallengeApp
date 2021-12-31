package com.te.photochallenge.model

import java.io.Serializable

data class Photo(val albumId: Int?,
                 val id: Int?,
                 val title: String?,
                 val url: String?,
                 val thumbnailUrl: String?
) : Serializable {}
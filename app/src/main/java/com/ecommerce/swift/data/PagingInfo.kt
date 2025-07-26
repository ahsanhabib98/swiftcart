package com.ecommerce.swift.data

data class PagingInfo(
    var pageCount: Long = 1,
    var oldItemList: List<Product> = emptyList(),
    var isPagingEnd: Boolean = false
)
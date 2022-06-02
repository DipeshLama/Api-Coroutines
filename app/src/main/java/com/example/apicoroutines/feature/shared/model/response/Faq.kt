package com.example.apicoroutines.feature.shared.model.response

data class Faq(
    var title: String? = null,
    var answer: String? = null,
    var isExpanded : Boolean = false
)
package com.example.cleancryptocurrency.domain.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}
package com.example.s215824_lykkehjulet.data

class Category {
    fun animals(): List<String> {
        return listOf("HUND", "KAT", "MUS")
    }
    fun cars(): List<String> {
        return listOf("BMW", "AUDI", "MERCEDES")
    }
    fun jobTitle(): List<String> {
        return listOf("LÆGE", "ADVOKAT", "LÆRER")
    }
}
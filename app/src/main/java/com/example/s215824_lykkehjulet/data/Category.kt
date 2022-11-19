package com.example.s215824_lykkehjulet.data

class Category {
    fun animals(): MutableList<String> {
        return mutableListOf("HUND", "KAT", "MUS")
    }
    fun cars(): MutableList<String> {
        return mutableListOf("BMW", "AUDI", "MERCEDES")
    }
    fun jobTitle(): MutableList<String> {
        return mutableListOf("LÆGE", "ADVOKAT", "LÆRER")
    }
    fun clothes(): MutableList<String> {
        return mutableListOf("TRØJE", "KASKET", "JAKKE")
    }
    fun cities(): MutableList<String> {
        return mutableListOf("FREDERIKSSUND", "BALLERUP", "LYNGBY")
    }
}
package com.example.s215824_lykkehjulet.data

/**
 * This class contains the different alphabets that a player might want to choose between.
 * For now, I've only added the Danish and English alphabet, but in future development, a
 * different alphabet can be added simply in another function.
 */
class Characters {
    fun loadDanishCharacters(): List<Char> {
        return listOf(
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I',
            'J',
            'K',
            'L',
            'M',
            'N',
            'O',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'V',
            'W',
            'X',
            'Y',
            'Z',
            'Æ',
            'Ø',
            'Å'
        )
    }

    // Made for future use:
    fun loadEnglishCharacters(): List<Char> {
        return listOf(
            'A',
            'B',
            'C',
            'D',
            'E',
            'F',
            'G',
            'H',
            'I',
            'J',
            'K',
            'L',
            'M',
            'N',
            'O',
            'P',
            'Q',
            'R',
            'S',
            'T',
            'U',
            'V',
            'W',
            'X',
            'Y',
            'Z'
        )
    }
}
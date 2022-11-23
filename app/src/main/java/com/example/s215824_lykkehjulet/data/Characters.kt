package com.example.s215824_lykkehjulet.data

/**
 * For future development, a different alphabet can be added in another function.
 * For now, I've only added the danish and english alphabet.
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
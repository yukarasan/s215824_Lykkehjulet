package com.example.s215824_lykkehjulet.data

/**
 * A dummy data class that stores 7 different categories. Each category have a list of words.
 */
class Category {
    fun animals(): MutableList<String> {
        return mutableListOf(
            "HUND",
            "KAT",
            "MUS",
            "ZEBRA",
            "SO",
            "TIGER",
            "RÆV",
            "HJORT",
            "KANIN",
            "HAMSTER",
            "ULE",
            "PINDSVIN",
            "FLODHEST",
            "ULV",
            "NÆBDYR"
        )
    }

    fun cars(): MutableList<String> {
        return mutableListOf(
            "BMW",
            "AUDI",
            "MERCEDES",
            "VOLKSWAGEN",
            "PEUGEOT",
            "SKODA",
            "VOLVO",
            "PORSCHE",
            "FERRARI",
            "TOYOTA",
            "NISSAN",
            "LEXUS",
            "MAZDA",
            "SEAT",
            "TESLA"
        )
    }

    fun jobTitle(): MutableList<String> {
        return mutableListOf(
            "LÆGE",
            "ADVOKAT",
            "LÆRER",
            "KØKKENANSVARLIG",
            "GASTRONOM",
            "BARTENDER",
            "OPVASKER",
            "SLAGTER",
            "SALGSASSISTENT",
            "FRISØR",
            "INGENIØR",
            "ELEKTRIKER",
            "TØMRER",
            "SNEDKER",
            "BETONSTØBER",
            "BROLÆGGER"
        )
    }

    fun clothes(): MutableList<String> {
        return mutableListOf(
            "TRØJE",
            "KASKET",
            "JAKKE",
            "KJOLE",
            "NEDERDEL",
            "JEANS",
            "SHORTS",
            "TOP",
            "BLUSE",
            "SPORTSTØJ",
            "UNDERTØJ",
            "BADETØJ",
            "SWEATER",
            "FRAKKE",
            "SPORTSTØJ"
        )
    }

    fun cities(): MutableList<String> {
        return mutableListOf(
            "FREDERIKSSUND",
            "BALLERUP",
            "LYNGBY",
            "KØBENHAVN",
            "HILLERØD",
            "HELSINGØR",
            "ROSKILDE",
            "RINGSTED",
            "SLAGELSE",
            "NÆSTVED",
            "ODENSE",
            "KOLDING",
            "VEJLE",
            "HORSENS",
            "AARHUS",
            "SILKEBORG",
            "ESBJERG",
            "RANDERS",
            "AALBORG"
        )
    }

    fun countries(): MutableList<String> {
        return mutableListOf(
            "DANMARK",
            "TYRKIET",
            "MADAGASCAR",
            "SYDAFRIKA",
            "ZIMBABWE",
            "INDIEN",
            "KINA",
            "JAPAN",
            "INDONESIEN",
            "AUSTRALIEN",
            "USA",
            "RUSLAND",
            "CANADA",
            "KAZAKHSTAN",
            "ISLAND",
            "STORBRITANNIEN",
            "NORGE",
            "SVERIGE",
            "BRASILIEN",
            "ARGENTINA",
            "MEXICO",
            "MAROKKO",
            "EGYPTEN",
            "KURDISTAN"
        )
    }

    fun worldCities(): MutableList<String> {
        return mutableListOf(
            "PARIS",
            "MADRID",
            "LISSABON",
            "LONDON",
            "AMSTERDAM",
            "BERLIN",
            "ISTANBUL",
            "TOKYO",
            "JAKARTA",
            "SYDNEY",
            "KAIRO",
            "MOSKVA",
            "MIAMI",
            "REYKJAVIK",
            "OSLO",
            "STOCKHOLM",
            "ANKARA",
            "BAGDAD",
            "DOHA",
            "DUBAI",
            "KABUL",
            "SHANGHAI",
            "SHENZHEN",
            "SEOUL"
        )
    }
}
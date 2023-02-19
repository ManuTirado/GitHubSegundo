package com.example.pruebarecyclerview

class SuperHeroProvider {
    companion object {
        val superHeroList = listOf<SuperHero>(
            SuperHero(
                "KotlinMan",
                "Jetbrains",
                "AristiDevs",
                "https://raw.githubusercontent.com/ManuelTirado/Fotos/main/user.png"
            ),
            SuperHero(
                "SpiderMan",
                "Marvel",
                "Peter Parker",
                "https://raw.githubusercontent.com/ManuelTirado/Fotos/main/juanjo.jpeg"
            ),
            SuperHero(
                "Daredevil",
                "Marvel",
                "Matthew",
                "https://raw.githubusercontent.com/ManuelTirado/Fotos/main/mando.png"
            ),
            SuperHero(
                "Wolverine",
                "Marvel",
                "James",
                "https://raw.githubusercontent.com/ManuelTirado/Fotos/main/photo.gif"
            ),
            SuperHero(
                "Batman",
                "DC",
                "Bruce Wayne",
                "https://raw.githubusercontent.com/ManuelTirado/Fotos/main/pickle.png"
            ),
            SuperHero(
                "Antman",
                "Marvel",
                "No se, Antonio",
                "https://raw.githubusercontent.com/ManuelTirado/Fotos/main/png-clipart-cristiano-ronaldo-football-player-uefa-champions-league-sport-real-madrid-c-f-cristiano-ronaldo-sports-shoe.png"
            )
        )
    }
}
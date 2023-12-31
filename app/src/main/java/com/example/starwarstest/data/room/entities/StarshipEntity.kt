package com.example.starwarstest.data.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwarstest.domain.model.Starship

@Entity(tableName = "starship_entity")
data class StarshipEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val model: String,
    val manufacturer: String,
    val passengers: Int
) {

    fun convertToStarship() : Starship = Starship(
        id = id,
        name = name,
        model = model,
        manufacturer = manufacturer,
        passengers = passengers
    )

    companion object {
        fun convertFromStarship(starship: Starship) : StarshipEntity = StarshipEntity(
            id = starship.id,
            name = starship.name,
            model = starship.model,
            manufacturer = starship.manufacturer,
            passengers = starship.passengers
        )
    }
}
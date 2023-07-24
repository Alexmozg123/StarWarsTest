package com.example.starwarstest.data.room.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.starwarstest.domain.model.People

@Entity(tableName = "people_entity")
data class PeopleEntity(
    @PrimaryKey(autoGenerate = false) val id: Int,
    val name: String,
    val gender: String,
    @ColumnInfo(name = "number_of_manned_ships") val numberOfMannedShips: Int,
) {

    fun convertToPeople(): People = People(
        id = id,
        name = name,
        gender = gender,
        numberOfMannedShips = numberOfMannedShips
    )

    companion object {
        fun convertFromPeople(people: People) : PeopleEntity = PeopleEntity(
            id = people.id,
            name = people.name,
            gender = people.gender,
            numberOfMannedShips = people.numberOfMannedShips
        )
    }
}
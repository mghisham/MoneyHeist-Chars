package apps.hm.mhchars.domain

import apps.hm.mhchars.domain.model.CharacterEntity

fun getDummyCharacters() = listOf(
    CharacterEntity(
        id = 1,
        name = "1",
        alias = "1",
        occupation = "1",
        gender = "1",
        status = "1",
        romance = "1",
        family = "1",
        first_appearance = "1",
        last_appearance = "1",
        played_by = "1",
        image = "1"
    )
)
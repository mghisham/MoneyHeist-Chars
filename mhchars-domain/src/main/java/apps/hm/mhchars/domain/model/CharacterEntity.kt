package apps.hm.mhchars.domain.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class CharacterEntity(
    val id: Int,
    val name: String,
    val alias: String,
    val occupation: String,
    val gender: String,
    val status: String,
    val romance: String,
    val family: String,
    val first_appearance: String,
    val last_appearance: String,
    val played_by: String,
    val image: String,
) : Parcelable

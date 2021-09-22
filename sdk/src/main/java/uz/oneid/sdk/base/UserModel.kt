package uz.oneid.sdk.base

data class UserModel(
    val pin: String? = null,
    val login : String? = null,
    val document: String? = null,
    val birthDate: String? = null,
    val birthPlaceId: String? = null,
    val birthCountryId: String? = null,
    val liveStatus: String? = null,
    val nationalityId: String? = null,
    val citizenshipId: String? = null,
    val docGivePlaceId: String? = null,
    val issueDate: String? = null,
    val endDate: String? = null,
    val photo: String? = null,
) : BaseModel()
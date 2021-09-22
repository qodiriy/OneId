package uz.oneid.sdk.auth.model

data class PassportData(
    val document: String? = null,
    val surname_latin: String? = null,
    val name_latin: String? = null,
    val patronym_latin: String? = null,
    val surname_engl: String? = null,
    val name_engl: String? = null,
    val birth_date: String? = null
)
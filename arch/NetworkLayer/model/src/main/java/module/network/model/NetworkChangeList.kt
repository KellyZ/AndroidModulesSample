package module.network.model

data class NetworkChangeList(
    val id: String,
    val changeListVersion: Int,
    val isDelete: Boolean,
)

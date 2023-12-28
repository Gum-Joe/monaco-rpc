import kotlinx.serialization.Serializable

@Serializable
data class FileOpenRequest(val path: String)
@Serializable

data class FileSaveRequest(val path: String, val contents: String)
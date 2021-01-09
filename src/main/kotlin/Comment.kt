data class Comment (val noteId: Long, val ownerId: Long, val replyTo: Long, val message: String) {
    var id: Long = 0
    var deleted: Boolean = false
}
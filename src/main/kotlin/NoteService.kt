object NoteService: CrudService<Note> {
    private var notes = emptyArray<Note>()
    private val commentService = CommentService

    override fun add(entity: Note) {
        notes.plus(entity)
    }

    override fun delete(id: Long) {
        notes.filter{it.id == id}[0].deleted = true
    }

    override fun edit(entity: Note) {
        notes += entity.copy(title = entity.title, text = entity.text)
    }

    override fun read(): List<Note> {
        return notes.filter{!it.deleted}
    }

    override fun getById(id: Long): Note {
        return notes.filter{it.id == id}[0]
    }

    override fun restore(id: Long) {
        notes.filter{it.id == id}[0].deleted = false
    }

    fun createComment(noteId: Long, ownerId: Long, replyTo: Long, message: String) {
        if(getById(noteId) == null) {
            return
        }

        val comment = Comment(noteId, ownerId, replyTo, message)
        commentService.add(comment)
    }

    fun deleteComment(id: Long) {
        commentService.delete(id)
    }

    fun editComment(id: Long, ownerId: Long, message: String) {
        val comment = commentService.getById(id).copy(ownerId = ownerId, message = message)
        commentService.edit(comment)
    }

    fun getComments(noteId: Long): List<Comment> {
        return commentService.read(getById(noteId))
    }
}
object CommentService: CrudService<Comment> {
    private var comments = emptyArray<Comment>()

    override fun add(entity: Comment) {
        comments.plus(entity)
    }

    override fun delete(id: Long) {
        comments.filter{it.id == id}[0].deleted = true
    }

    override fun edit(entity: Comment) {
        comments += entity.copy(message = entity.message)
    }

    override fun read(): List<Comment> {
        return comments.filter{!it.deleted}
    }

    fun read(note: Note): List<Comment> {
        return comments.filter{!it.deleted && it.noteId == note.id}
    }

    override fun getById(id: Long): Comment {
        return comments.filter{it.id == id}[0]
    }

    override fun restore(id: Long) {
        comments.filter{it.id == id}[0].deleted = false
    }
}
interface CrudService<E> {
    fun add(entity: E)
    fun delete(id: Long)
    fun edit(entity: E)
    fun read(): List<E>
    fun getById(id: Long): E
    fun restore(id: Long)
}
package query

interface QueryHandler<Q: Query> {
    fun handleQuery(query: Query)
}
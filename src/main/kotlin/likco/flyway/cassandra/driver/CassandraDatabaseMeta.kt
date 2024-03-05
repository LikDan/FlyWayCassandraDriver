package likco.flyway.cassandra.driver

import java.sql.DatabaseMetaData

class CassandraDatabaseMeta(private val meta: DatabaseMetaData) : DatabaseMetaData by meta {
    override fun getDatabaseProductName(): String {
        return "SQLite (Cassandra)"
    }
}
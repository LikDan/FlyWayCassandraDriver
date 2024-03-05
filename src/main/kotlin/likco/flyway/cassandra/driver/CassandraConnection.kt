package likco.flyway.cassandra.driver

import likco.flyway.cassandra.driver.parser.query.parseSQL
import java.sql.Connection
import java.sql.DatabaseMetaData
import java.sql.PreparedStatement
import java.sql.Statement

class CassandraConnection(private val connection: Connection) : Connection by connection {
    override fun prepareStatement(sql: String?): PreparedStatement {
        return CassandraPreparedStatement(connection.prepareStatement(parseSQL(sql)))
    }

    override fun createStatement(): Statement {
        return CassandraStatement(connection.createStatement())
    }

    override fun getMetaData(): DatabaseMetaData {
        return CassandraDatabaseMeta(connection.metaData)
    }
}
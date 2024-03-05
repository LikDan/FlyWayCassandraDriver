package likco.flyway.cassandra.driver

import java.sql.PreparedStatement
import java.sql.ResultSet

class CassandraPreparedStatement(private val statement: PreparedStatement) : PreparedStatement by statement {
    override fun executeQuery(sql: String?): ResultSet {
        return CassandraResultSet(statement.executeQuery(sql))
    }

    override fun executeQuery(): ResultSet {
        return CassandraResultSet(statement.executeQuery())
    }

    override fun setEscapeProcessing(enable: Boolean) {
    }
}
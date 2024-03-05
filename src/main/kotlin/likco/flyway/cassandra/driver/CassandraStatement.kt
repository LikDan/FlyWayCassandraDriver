package likco.flyway.cassandra.driver

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

class CassandraStatement(private val statement: Statement) : Statement by statement {
    override fun executeQuery(sql: String?): ResultSet {
        return CassandraResultSet(statement.executeQuery(sql))
    }

    override fun setEscapeProcessing(enable: Boolean) {
    }
}
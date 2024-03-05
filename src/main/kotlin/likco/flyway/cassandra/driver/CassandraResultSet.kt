package likco.flyway.cassandra.driver

import java.sql.ResultSet

class CassandraResultSet(private val resultSet: ResultSet) : ResultSet by resultSet {
    override fun getInt(columnIndex: Int): Int {
        return runCatching {
            resultSet.getInt(columnIndex)
        }.getOrElse {
            resultSet.getLong(columnIndex).toInt()
        }
    }
}
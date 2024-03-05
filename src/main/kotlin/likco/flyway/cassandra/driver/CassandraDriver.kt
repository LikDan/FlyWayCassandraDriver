package likco.flyway.cassandra.driver

import com.wisecoders.dbschema.cassandra.JdbcDriver
import likco.flyway.cassandra.driver.parser.query.migrationKeyspaceCreationQuery
import likco.flyway.cassandra.driver.parser.query.migrationTableCreationQuery
import java.sql.Connection
import java.sql.Driver
import java.util.*

class CassandraDriver(private val driver: JdbcDriver = JdbcDriver()) : Driver by driver {
    override fun connect(url: String?, info: Properties?): Connection {
        val cassandraURL = url?.replace("jdbc:sqlite://", "jdbc:cassandra://")?.dropLastWhile { it != '/' }
        val dc = url?.substringAfterLast("/") ?: ""
        info?.setProperty("dc", dc)

        return CassandraConnection(driver.connect(cassandraURL, info)).apply {
            initMigrationsIfNotExists(this)
        }
    }

    companion object {
        private fun initMigrationsIfNotExists(connection: Connection) {
            connection.createStatement().executeQuery(migrationKeyspaceCreationQuery)
            connection.createStatement().executeQuery(migrationTableCreationQuery)
        }
    }
}
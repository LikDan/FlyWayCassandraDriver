import likco.flyway.cassandra.driver.CassandraDriver
import org.flywaydb.core.Flyway
import org.flywaydb.core.internal.jdbc.DriverDataSource

fun main() {
    Flyway
        .configure()
        .driver("likco.flyway.cassandra.driver.CassandraDriver")
        .dataSource(
            DriverDataSource(
                CassandraDriver::class.java.classLoader,
                "likco.flyway.cassandra.driver.CassandraDriver",
                "jdbc:sqlite://host:port/datacenter",
                "<<user>>",
                "<<password>>"
            )
        )
        .locations("filesystem:./sql")
        .load()
        .migrate()

}
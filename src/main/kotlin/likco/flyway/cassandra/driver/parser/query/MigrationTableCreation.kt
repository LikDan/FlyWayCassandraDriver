package likco.flyway.cassandra.driver.parser.query

val migrationKeyspaceCreationQuery = """
    CREATE KEYSPACE IF NOT EXISTS migrations WITH REPLICATION = {
        'class' : 'SimpleStrategy',
        'replication_factor' : 1
        };
""".trimIndent()

val migrationTableCreationQuery = """
    CREATE TABLE IF NOT EXISTS migrations.flyway_schema_history
    (
        installed_rank int,
        version        varchar,
        description    varchar,
        type           varchar,
        script         varchar,
        checksum       int,
        installed_by   varchar,
        installed_on   timestamp,
        execution_time int,
        success        boolean,

        PRIMARY KEY ( version, installed_rank )
    ) WITH CLUSTERING ORDER BY (installed_rank ASC );
""".trimIndent()
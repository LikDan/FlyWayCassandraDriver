package likco.flyway.cassandra.driver.parser.query

fun parseSQL(sql: String?): String = when {
    sql == null -> ""
    sql.startsWith("SELECT tbl_name FROM") -> "SELECT table_name FROM system_schema.tables;"
    sql.startsWith("SELECT count(tbl_name) FROM") -> "SELECT COUNT(*) FROM system_schema.tables WHERE table_name = 'flyway_schema_history';\n"
    sql.startsWith("SELECT \"installed_rank\"") -> """
        SELECT "installed_rank",
               "version",
               "description",
               "type",
               "script",
               "checksum",
               "installed_on",
               "installed_by",
               "execution_time",
               "success"
        FROM "migrations"."flyway_schema_history"
        WHERE "installed_rank" > ?;
    """.trimIndent()
    sql.startsWith("INSERT INTO \"main\"") -> """
INSERT INTO "migrations"."flyway_schema_history" ("installed_rank", "version", "description", "type", "script", "checksum", "installed_on", "installed_by", "execution_time", "success") VALUES (?, ?, ?, ?, ?, ?, toTimestamp(now()), ?, ?, ?)
    """.trimIndent()
    else -> {
        println("unparsed query: ${sql}")
        sql
    }
}
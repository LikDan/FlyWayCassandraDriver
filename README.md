# FlyWay Cassandra driver

This is driver for FlyWay migration tool

The way it works is pretty interesting
Because we cannot write custom flyway type without rebuilding whole flyway tool
I wrote a driver that imitate SQLite.

Basically is takes SQLite Query and converts it to Cassandra CQL Query

It is not really scalable
because [FlyWay promise to add Cassandra](https://documentation.red-gate.com/flyway/flyway-blog/flyway-v10-has-landed)
support pretty soon.

It works out of box, but you cannot customise it, for example change keyspace or table names, to do this you need to
download source code change params there and rebuild projects using this command

```
 gradle jar
```

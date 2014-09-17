sabongpro
=========

If you would like to insert default values, use Flyway Migrations.

1. mvn compile flyway:init
2. mvn compile flyway:migrate

The following will execute the scripts found in `src/com/resources/db/migrations`

### WARNING: NEVER EDIT THE SQL, THIS WILL INVOLVE A LOT OF FLYWAY REPAIRING

If in case you encounter a lot of errors in flyway migrations, you can revert everything (including the schema)

1. mvn compile flyway:repair
2. mvn compile flyway:clean

This will delete your schema, so you have to run the project again (to recreate the schema) and then execute the flyway migration commands.

More SQL scripts to come.
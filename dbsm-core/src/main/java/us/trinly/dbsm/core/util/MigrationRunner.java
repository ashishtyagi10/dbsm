package us.trinly.dbsm.core.util;

import java.io.ByteArrayInputStream;

import javax.sql.DataSource;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;

public class MigrationRunner {
    public void applyChanges(String changelogPath, DataSource dataSource) {
        try {
            Database database = DatabaseFactory.getInstance()
                .findCorrectDatabaseImplementation(
                    new liquibase.database.jvm.JdbcConnection(dataSource.getConnection()));
            Liquibase liquibase = new Liquibase(
                changelogPath,
                new ClassLoaderResourceAccessor(),
                database
            );
            liquibase.update((String) null);
        } catch (Exception e) {
            throw new RuntimeException("Failed to apply database migrations", e);
        }
    }
}

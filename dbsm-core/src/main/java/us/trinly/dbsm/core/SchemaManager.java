package us.trinly.dbsm.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import liquibase.exception.LiquibaseException;
import us.trinly.dbsm.core.exporter.LiquibaseExporter;
import us.trinly.dbsm.core.generator.DatabaseModel;
import us.trinly.dbsm.core.generator.SchemaDiff;
import us.trinly.dbsm.core.scanner.EntityScanner;
import us.trinly.dbsm.core.util.MigrationRunner;
import us.trinly.dbsm.core.generator.SchemaGenerator;

/**
 * A simple placeholder class in the core module.
 */
public class SchemaManager {
    public void generateAndApplyMigrations(String entityPackage, DataSource dataSource) throws LiquibaseException, ClassNotFoundException, IOException {
        // Scan entities
        List<Class<?>> entities = new EntityScanner().scanEntities(entityPackage);
        
        // Generate model
        DatabaseModel model = new SchemaGenerator().generateSchema(entities);
        
        // Compare with database
        SchemaDiff diff = new SchemaGenerator().compareWithDatabase(model, dataSource);
        
        if (!diff.isEmpty()) {
            // Generate Liquibase changelog
            ByteArrayOutputStream changelog = new ByteArrayOutputStream();
            new LiquibaseExporter().generateChangeLog(diff, changelog);
            
            // Apply changes
            new MigrationRunner().applyChanges(
                "db/changelog/db.changelog-master.xml",
                dataSource);
        }
    }
}
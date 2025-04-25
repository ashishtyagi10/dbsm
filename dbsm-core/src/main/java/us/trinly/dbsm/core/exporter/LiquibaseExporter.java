package us.trinly.dbsm.core.exporter;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import liquibase.exception.LiquibaseException;
import liquibase.changelog.DatabaseChangeLog;
import liquibase.changelog.ChangeSet;
import liquibase.change.core.CreateTableChange;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.serializer.ChangeLogSerializer;
// TODO: Import or implement SchemaDiff and TableDiff if they exist in your project
import us.trinly.dbsm.core.generator.SchemaDiff;
import us.trinly.dbsm.core.generator.TableDiff;

public class LiquibaseExporter {
    public void generateChangeLog(SchemaDiff diff, OutputStream output) 
        throws LiquibaseException, IOException {
        
        DatabaseChangeLog changeLog = new DatabaseChangeLog();
        
        for (TableDiff tableDiff : diff.getTableDiffs()) {
            if (tableDiff.isNewTable()) {
                CreateTableChange createTable = new CreateTableChange();
                createTable.setTableName(tableDiff.getName());
                // Add columns, constraints etc.
                ChangeSet changeSet = new ChangeSet(
                    "id-" + tableDiff.getName(), // id
                    "auto",                      // author
                    false, false, null,                         // filePath
                    null,                         // contextList
                    null,                         // dbmsList
                    false,                        // runInTransaction
                    null                          // objectQuotingStrategy
                );
                changeSet.addChange(createTable);
                changeLog.addChangeSet(changeSet);
            }
            // Handle other change types
        }
        
        // Serialize the DatabaseChangeLog to the output stream using Liquibase's XMLChangeLogSerializer
        liquibase.serializer.core.xml.XMLChangeLogSerializer serializer = new liquibase.serializer.core.xml.XMLChangeLogSerializer();
        serializer.write(changeLog.getChangeSets(), output);
    }
}

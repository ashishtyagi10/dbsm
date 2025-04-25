package us.trinly.dbsm.core.generator;

import java.util.List;

public class SchemaDiff {
    private List<TableDiff> tableDiffs;

    public SchemaDiff(List<TableDiff> tableDiffs) {
        this.tableDiffs = tableDiffs;
    }

    public List<TableDiff> getTableDiffs() {
        return tableDiffs;
    }
    // Add fields and methods as needed

    public boolean isEmpty() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isEmpty'");
    }
}

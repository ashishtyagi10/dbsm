package us.trinly.dbsm.core.generator;

public class TableDiff {
    private String name;
    private boolean newTable;

    public TableDiff(String name, boolean newTable) {
        this.name = name;
        this.newTable = newTable;
    }

    public String getName() {
        return name;
    }

    public boolean isNewTable() {
        return newTable;
    }

    // Add more fields and methods as needed
}

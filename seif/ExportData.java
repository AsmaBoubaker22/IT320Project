package com.mycompany.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class ExportData {

    /**
     * Export the given data to SQL with "INSERT IGNORE" to avoid duplicates.
     *
     * @param tableName The name of the table in MySQL.
     * @param data      A List of rows, where row 0 is the column names, row 1..N are data rows.
     */
    public void ExportToSQL(String tableName,
                            List<List<Object>> data,
                            String jdbcUrl,
                            String username,
                            String password) {
        if (data == null || data.isEmpty()) {
            System.out.println("No data to export for table " + tableName + ".");
            return;
        }

        // The first row is the header (column names)
        List<Object> headers = data.get(0);

        // Build the columns & placeholder strings
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        for (int i = 0; i < headers.size(); i++) {
            columns.append(headers.get(i).toString());
            placeholders.append('?');
            if (i < headers.size() - 1) {
                columns.append(", ");
                placeholders.append(", ");
            }
        }

        // Use INSERT IGNORE so duplicates won't cause errors
        String insertSQL = "INSERT IGNORE INTO " + tableName +
                           " (" + columns + ") VALUES (" + placeholders + ")";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
             PreparedStatement stmt = connection.prepareStatement(insertSQL)) {

            // Process each data row (start i=1 to skip header row)
            for (int i = 1; i < data.size(); i++) {
                List<Object> row = data.get(i);
                for (int j = 0; j < row.size(); j++) {
                    Object cell = row.get(j);
                    if (cell == null) {
                        stmt.setNull(j + 1, Types.NULL);
                    } else {
                        stmt.setString(j + 1, cell.toString());
                    }
                }
                stmt.addBatch();
            }

            int[] result = stmt.executeBatch();
            System.out.println(result.length + " rows processed for "
                + tableName + " (INSERT IGNORE).");

        } catch (SQLException e) {
            System.out.println("Error exporting to SQL for table " + tableName + ": " + e.getMessage());
        }
    }
}

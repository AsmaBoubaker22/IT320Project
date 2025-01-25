package com.mycompany.main;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) {

        // 1) Ingest CSV
        CSVingestion csvIngestion = new CSVingestion();
        String csvFilePath = "SampleDataCSV.csv";
        List<List<String>> csvData = csvIngestion.csvImport(csvFilePath);

        // Data cleaner
        propertyDataCleaner cleaner = new propertyDataCleaner();

        // 2) Clean CSV
        cleaner.removeMissingValues(csvData);
        cleaner.removeDuplicates(csvData);
        cleaner.removeOutliers(csvData);
        cleaner.fixYear(csvData);

        // 3) Inject CSV data
        csvIngestion.csvInject(csvData);

        // 4) Ingest XLS
        XLSingestion xlsIngestion = new XLSingestion();
        String xlsFilePath = "SampleDataXLSX.xlsx";
        List<List<String>> xlsData = xlsIngestion.xlsImport(xlsFilePath);

        // 5) Clean XLS
        cleaner.removeMissingValues(xlsData);
        cleaner.removeDuplicates(xlsData);
        cleaner.removeOutliers(xlsData);
        cleaner.fixYear(xlsData);

        // 6) Inject XLS data
        xlsIngestion.xlsInject(xlsData);

        // 7) Load MySQL driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found: " + e.getMessage());
            return;
        }

        // 8) Export data to SQL
        ExportData exporter = new ExportData();
        String jdbcUrl = "jdbc:mysql://localhost:3306/samplesql";
        String username = "root";
        String password = "0000";

        System.out.println("Exporting CSV data to SQL...");
        exporter.ExportToSQL("Buyer", convertToListOfLists(csvIngestion.buyersList), jdbcUrl, username, password);
        exporter.ExportToSQL("Agent", convertToListOfLists(csvIngestion.agentsList), jdbcUrl, username, password);
        exporter.ExportToSQL("House", convertToListOfLists(csvIngestion.housesList), jdbcUrl, username, password);
        exporter.ExportToSQL("Location", convertToListOfLists(csvIngestion.locationList), jdbcUrl, username, password);
        exporter.ExportToSQL("Unit", convertToListOfLists(csvIngestion.unitsList), jdbcUrl, username, password);
        exporter.ExportToSQL("Townhouse", convertToListOfLists(csvIngestion.townhousesList), jdbcUrl, username, password);
        exporter.ExportToSQL("DevelopmentSite", convertToListOfLists(csvIngestion.developmentSitesList), jdbcUrl, username, password);
        exporter.ExportToSQL("Transaction", convertToListOfLists(csvIngestion.transactionsList), jdbcUrl, username, password);

        System.out.println("Exporting XLS data to SQL...");
        exporter.ExportToSQL("Buyer", convertToListOfLists(xlsIngestion.buyersList), jdbcUrl, username, password);
        exporter.ExportToSQL("Agent", convertToListOfLists(xlsIngestion.agentsList), jdbcUrl, username, password);
        exporter.ExportToSQL("House", convertToListOfLists(xlsIngestion.housesList), jdbcUrl, username, password);
        exporter.ExportToSQL("Location", convertToListOfLists(xlsIngestion.locationList), jdbcUrl, username, password);
        exporter.ExportToSQL("Unit", convertToListOfLists(xlsIngestion.unitsList), jdbcUrl, username, password);
        exporter.ExportToSQL("Townhouse", convertToListOfLists(xlsIngestion.townhousesList), jdbcUrl, username, password);
        exporter.ExportToSQL("DevelopmentSite", convertToListOfLists(xlsIngestion.developmentSitesList), jdbcUrl, username, password);
        exporter.ExportToSQL("Transaction", convertToListOfLists(xlsIngestion.transactionsList), jdbcUrl, username, password);
    }

    /**
     * Convert a list of Java objects into List<List<Object>> for ExportData.
     * Skips only the primary-key fields (auto-increment) from reflection:
     *   houseId, unitId, townhouseId, developmentSiteId, transactionId,
     *   locationId, buyerId, agentId -- if you want MySQL to auto-generate them.
     *
     * If your code references buyerId or agentId from CSV, remove them from "skip" list.
     * If "address" is spelled differently in your classes, rename either the field or the column.
     */
    public static List<List<Object>> convertToListOfLists(List<?> objects) {
        List<List<Object>> tableData = new ArrayList<>();
        if (objects == null || objects.isEmpty()) {
            return tableData;
        }

        // Reflect on the fields of the first object
        Field[] allFields = objects.get(0).getClass().getDeclaredFields();
        List<String> headers = new ArrayList<>();
        List<Field> usableFields = new ArrayList<>();

        for (Field f : allFields) {
            f.setAccessible(true);
            String fieldName = f.getName();

            // Skip the real auto-increment PK fields
            // (If you want to preserve them from CSV, comment out the relevant lines.)
            if (fieldName.equalsIgnoreCase("houseId") ||
                fieldName.equalsIgnoreCase("unitId") ||
                fieldName.equalsIgnoreCase("townhouseId") ||
                fieldName.equalsIgnoreCase("developmentSiteId") ||
                fieldName.equalsIgnoreCase("transactionId") ||
                fieldName.equalsIgnoreCase("locationId")) {
                continue;
            }

            // Also skip any "properties" or List<?> typed fields
            if ("properties".equals(fieldName) ||
                List.class.isAssignableFrom(f.getType())) {
                continue;
            }

            // Keep everything else, including "address" for House/Unit/Townhouse
            headers.add(fieldName);
            usableFields.add(f);
        }

        // First row: column names
        tableData.add(new ArrayList<>(headers));

        // Build the data rows
        for (Object obj : objects) {
            List<Object> row = new ArrayList<>();
            for (Field field : usableFields) {
                try {
                    Object value = field.get(obj);

                    if (value == null) {
                        row.add(null);
                        continue;
                    }

                    // Convert boolean -> "0"/"1"
                    if (field.getType() == boolean.class || field.getType() == Boolean.class) {
                        boolean boolVal = (boolean) value;
                        row.add(boolVal ? "1" : "0");
                        continue;
                    }

                    // If field name is "date", parse from dd/MM/yyyy or MM/dd/yyyy => yyyy-MM-dd
                    if (field.getName().equalsIgnoreCase("date")) {
                        String dateStr = value.toString().trim();
                        String isoDate = parseToISODate(dateStr);
                        row.add(isoDate.isEmpty() ? null : isoDate);
                        continue;
                    }

                    // Otherwise, just convert to string
                    row.add(value.toString());

                } catch (IllegalAccessException e) {
                    row.add(null);
                }
            }
            tableData.add(row);
        }

        return tableData;
    }

    /**
     * Try dd/MM/yyyy or MM/dd/yyyy => yyyy-MM-dd. Return "" if invalid/empty.
     */
    private static String parseToISODate(String raw) {
        if (raw == null || raw.isEmpty()) {
            return "";
        }
        String[] patterns = {"dd/MM/yyyy", "MM/dd/yyyy"};
        for (String pattern : patterns) {
            try {
                java.time.format.DateTimeFormatter inFmt =
                    java.time.format.DateTimeFormatter.ofPattern(pattern);
                java.time.LocalDate date = java.time.LocalDate.parse(raw, inFmt);
                return date.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE);
            } catch (Exception e) {
                // keep trying next pattern
            }
        }
        return "";
    }
}

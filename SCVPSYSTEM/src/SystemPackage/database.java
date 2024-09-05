package SystemPackage;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class database {

    private static final String URL = "jdbc:sqlite:database/database.db";

    public static Connection connect() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
        return connection;
    }

    public static void insert(String table, Map<String, Object> values) {
        StringBuilder columns = new StringBuilder();
        StringBuilder placeholders = new StringBuilder();

        values.forEach((key, value) -> {
            columns.append(key).append(",");
            placeholders.append("?,");
        });

        String sql = "INSERT INTO " + table + " (" +
                columns.substring(0, columns.length() - 1) + ") VALUES (" +
                placeholders.substring(0, placeholders.length() - 1) + ")";

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int index = 1;
            for (Object value : values.values()) {
                pstmt.setObject(index++, value);
            }
            pstmt.executeUpdate();
            System.out.println("Insert successful.");
        } catch (SQLException e) {
            System.err.println("Insert failed: " + e.getMessage());
        }
    }
    
    public static void delete(String table, String condition) {
        String sql = "DELETE FROM " + table + " WHERE " + condition;
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
            System.out.println("Delete successful.");
        } catch (SQLException e) {
            System.err.println("Delete failed: " + e.getMessage());
        }
    }

    

    public static List<Map<String, Object>> select(String table, String columns, String condition) {
        String sql = "SELECT " + columns + " FROM " + table + 
                     (condition != null ? " WHERE " + condition : "");
        List<Map<String, Object>> results = new ArrayList<>();
        try (Connection conn = connect(); 
             PreparedStatement pstmt = conn.prepareStatement(sql); 
             ResultSet rs = pstmt.executeQuery()) {
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    row.put(metaData.getColumnName(i), rs.getObject(i));
                }
                results.add(row);
            }
        } catch (SQLException e) {
            System.err.println("Select failed: " + e.getMessage());
        }
        return results;
    }


    public static void update(String table, Map<String, Object> values, String condition) {
        StringBuilder setClause = new StringBuilder();
        values.forEach((key, value) -> setClause.append(key).append(" = ?,"));

        String sql = "UPDATE " + table + " SET " +
                setClause.substring(0, setClause.length() - 1) + 
                (condition != null ? " WHERE " + condition : "");

        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            int index = 1;
            for (Object value : values.values()) {
                pstmt.setObject(index++, value);
            }
            pstmt.executeUpdate();
            System.out.println("Update successful.");
        } catch (SQLException e) {
            System.err.println("Update failed: " + e.getMessage());
        }
    }

  
}

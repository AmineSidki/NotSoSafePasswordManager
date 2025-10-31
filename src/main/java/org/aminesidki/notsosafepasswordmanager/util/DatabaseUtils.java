package org.aminesidki.notsosafepasswordmanager.util;

import org.aminesidki.notsosafepasswordmanager.HelloApplication;

import java.net.URL;
import java.sql.*;
import java.util.HashMap;

public class DatabaseUtils {
    public static Connection conn = null;

    public static void initializeConnection(){
        URL db = HelloApplication.class.getResource("/db/nsspm.db");
        String url = "jdbc:sqlite:" + db;

        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public static void insert(String key , String value) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("INSERT INTO PASSWORDS VALUES (? , ?);");
        statement.setString(1,key);
        statement.setString(2,value);
        statement.execute();

        statement.close();
    }

    public static HashMap<String , String> fetch() throws SQLException {
        Statement statement = conn.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM PASSWORDS;");

       HashMap<String ,String> passwords = new HashMap<>();
        while(rs.next()){
            passwords.put(rs.getString(1) , rs.getString(2));
        }

        statement.close();
        return passwords;
    }

    public static void update(String key , String value) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("UPDATE PASSWORDS SET password = ? WHERE application = ?;");
        statement.setString(1,value);
        statement.setString(2,key);
        statement.execute();

        statement.close();
    }

    public static void delete(String key) throws SQLException {
        PreparedStatement statement = conn.prepareStatement("DELETE FROM PASSWORDS WHERE application = ?;");
        statement.setString(1,key);
        statement.execute();

        statement.close();
    }
}

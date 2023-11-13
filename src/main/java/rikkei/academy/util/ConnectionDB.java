package rikkei.academy.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {

    private static final String DRIVER_JBDC = "com.mysql.cj.jdbc.Driver";
    private static final String USER_JBDC = "root";
    private static final String PASS_JBDC = "hoangutck57";
    private static final String URL_JBDC = "jdbc:mysql://localhost:3306/session09_student";

    // mở luồng
    public static Connection openConnection(){
        Connection connection = null;
        try {
            //
            Class.forName(DRIVER_JBDC);
            connection = DriverManager.getConnection(URL_JBDC,USER_JBDC,PASS_JBDC);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public static void closeConnection(Connection connection) {
        try {
            if ( connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        System.out.println(ConnectionDB.openConnection());
    }
}

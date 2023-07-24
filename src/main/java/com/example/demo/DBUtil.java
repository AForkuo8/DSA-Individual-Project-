package com.example.demo;

import java.sql.*;
import com.sun.rowset.CachedRowSetImpl;

public class DBUtil {

    private static Connection connection = null;

    public static void dbConnect() throws SQLException,ClassNotFoundException{

        String DBurl = "jdbc:mysql://localhost:3306";
        String DBuser = "root";
        String DBpassword = "";

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch (ClassNotFoundException e){
            System.out.println("JDBC CANNOT BE FOUND");
            e.printStackTrace();
            throw e;
        }

        System.out.println("Driver registered");

        try{
            connection = DriverManager.getConnection(DBurl, DBuser, DBpassword);
        }catch (SQLException e){
            System.out.println("Connection failed");
            e.printStackTrace();
            throw e;
        }
    }


    public static void dbDisconnect() throws SQLException{
        try{
            if  (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }catch (Exception e){
            throw e;
        }
    }

    public static CachedRowSetImpl dbExecuteQuery(String queryStmt) throws SQLException, ClassNotFoundException {
        Statement stmt = null;
        ResultSet resultSet = null;
        CachedRowSetImpl crs = null;

        try {
            dbConnect();
            System.out.println("Select statement: " + queryStmt + "\n");

            stmt = connection.createStatement();

            crs = new CachedRowSetImpl();
            crs.populate(resultSet);
        }catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        }finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (stmt != null) {
                stmt.close();
            }

            dbDisconnect();
        }

        return crs;
    }

    public static void dbExecuteUpdate(String sqlStmt) throws SQLException, ClassNotFoundException {

        Statement stmt = null;
        try {
            dbConnect();

            stmt = connection.createStatement();

            stmt.executeUpdate(sqlStmt);
        }catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        }finally {
             if (stmt != null) {
                 stmt.close();
             }
             dbConnect();
        }
    }
}

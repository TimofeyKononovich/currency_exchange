package com.example.currency_exchange;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCSource {
 private static  final String URL="jdbc:mysql://localhost:3306/currency_exchange";
 private static final String USERNAME="root";
 private static final String PASSWORD="1111";

 public JDBCSource() {

 }


 public static Connection getConnection() throws SQLException{
  return DriverManager.getConnection(URL, USERNAME, PASSWORD);
 }
}

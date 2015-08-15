package net.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class JdbcUtils {
 
 public static Connection getConnection(){
  Connection conn = null;
  String url="jdbc:mysql://localhost:3306/mldn?useUnicode=true&characterEncoding=utf8";
  String name = "root";
  String password = "root";
  try {
   Class.forName("com.mysql.jdbc.Driver");
   conn = DriverManager.getConnection(url,name,password);
  } catch (ClassNotFoundException e) {
   e.printStackTrace();
  } catch (SQLException e) {
   e.printStackTrace();
  }
  return conn;
 }
 
 public static void close(Connection conn) {
  if(conn != null) {
   try {
    conn.close();
   } catch(Exception e) {
    e.printStackTrace();
   }
  }
 }
 
 public static void close(Statement stmt) {
  if(stmt != null) {
   try {
    stmt.close();
   } catch (Exception e) {
    e.printStackTrace();
   }
  }
 }
 
 public static void close(ResultSet rs) {
  if(rs != null) {
   try {
    rs.close();
   } catch(Exception e) {
    e.printStackTrace();
   }
  }
 }
}
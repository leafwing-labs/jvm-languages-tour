package tech.leafwinglabs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import tech.leafwinglabs.product.Product;

public class DatabaseConnection_1_2 {

  public List getProducts() {
    String url = "jdbc:mysql://localhost:3306/yourdatabase";                       // connection data
    String user = "yourusername";
    String password = "yourpassword";
    Connection connection = null;
    List productList = new ArrayList();                                            // STATE

    try {
      Class.forName("com.mysql.jdbc.Driver");                            // class loading
      connection = DriverManager.getConnection(url, user, password);               // jdbc loading
      Statement statement = connection.createStatement();                          // transaction creation
      ResultSet resultSet = statement.executeQuery("SELECT * FROM product ");  // LOGIC
      while (resultSet.next()) {
        productList.add(new Product(resultSet));                                   // ACTION
      }
      resultSet.close();                                                           // reclaim
      statement.close();                                                           // reclaim
    } catch (ClassNotFoundException e) {                                           // exception handling
      System.err.println("JDBC Driver not found: " + e.getMessage());
    } catch (SQLException e) {
      System.err.println("SQL error: " + e.getMessage());
    } finally {
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          System.err.println("Error closing connection: " + e.getMessage());
        }
      }
    }
    return productList;                                                            // EXIT
  }

}

package tech.leafwinglabs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import jakarta.ejb.Stateless;
import tech.leafwinglabs.product.Product;

@Stateless
public class DatabaseConnection_1_5 {

  public List<Product> getProducts() {
    List<Product> productList = new ArrayList<Product>();

    try {
      InitialContext ctx = new InitialContext();                                       // JNDI interface
      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myDatasource"); // JNDI binding
      Connection connection = ds.getConnection();

      PreparedStatement ps = connection.prepareStatement("select * from product"); // LOGIC
      ResultSet rs = ps.executeQuery();
      productList.add(new Product(rs));                                                // ACTION

      rs.close();                                                                      // reclaim
      ps.close();
      connection.close();
      System.out.println("Database connection closed.");
    } catch (Exception e) {
      System.out.println("Failed to connect to the database");
      e.printStackTrace();
    }

    return productList;                                                               // EXIT
  }
}

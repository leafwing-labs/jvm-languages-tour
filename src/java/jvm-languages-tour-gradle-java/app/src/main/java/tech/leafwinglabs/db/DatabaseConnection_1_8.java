package tech.leafwinglabs.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import tech.leafwinglabs.product.Product;

public class DatabaseConnection_1_8 {

  public List<Product> getProducts() {
    try {
      InitialContext ctx = new InitialContext();                                           // JNDI interface
      DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/myDatasource");
      try (                                                                                // resource acquisition
          Connection connection = ds.getConnection();
          PreparedStatement ps = connection.prepareStatement("select * from product"); // LOGIC
          ResultSet rs = ps.executeQuery( )                                                // ACTION
      ) {
        return Stream.of(rs)                                                               // EXIT
                     .map(Product::new)
                     .collect(Collectors.toList());
      }
    } catch (Exception e) {
      System.out.println("Failed to connect to the database using J2EE and Java 1.8.");
      e.printStackTrace();
    }
    return Collections.emptyList();                                                        // EXIT
  }

}
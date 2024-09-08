package tech.leafwinglabs.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;

import tech.leafwinglabs.product.Product;

public class DatabaseConnection_1_5_Spring {

  private DataSource dataSource;

  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
  }

  public List<Product> queryProductTable() {
    JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);  // port
    List<Product> productList = new ArrayList<Product>();

    String selectQuery = "SELECT * FROM product";              // LOGIC
    jdbcTemplate.query(
      selectQuery,
      new RowCallbackHandler() {
        @Override
        public void processRow(ResultSet rs) {
          productList.add(new Product(rs));                    // ACTION
        }
      }
    );

    return productList;                                        // EXIT
  }

}
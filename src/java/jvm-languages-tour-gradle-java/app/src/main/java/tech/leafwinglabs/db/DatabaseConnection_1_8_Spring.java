package tech.leafwinglabs.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import tech.leafwinglabs.product.Product; 

@Component
public class DatabaseConnection_1_8_Spring {

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public DatabaseConnection_1_8_Spring(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Product> queryProductTable() {
    String selectQuery = "SELECT * FROM product";
    return jdbcTemplate.query(selectQuery, (rs, rowNum) -> new Product(rs));
  }

}
package tech.leafwinglabs.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DatabaseConnection_21 {

  public record Product(int id, String name, double price) {
    public Product(ResultSet rs) throws SQLException {
      this(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
    }
  }

  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public DatabaseConnection_21(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Product> queryProductTable() {
    String selectQuery = "SELECT * FROM product";
    return jdbcTemplate.query(selectQuery, (rs, rowNum) -> new Product(rs));
  }
}

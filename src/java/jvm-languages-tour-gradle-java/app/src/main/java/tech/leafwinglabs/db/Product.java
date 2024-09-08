package tech.leafwinglabs.db;

import java.sql.ResultSet;
import java.sql.SQLException;

public record Product(int id, String name, double price) {
  public Product(ResultSet rs) throws SQLException {
    this(rs.getInt("id"), rs.getString("name"), rs.getDouble("price"));
  }
}
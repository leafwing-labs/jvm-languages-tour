package tech.leafwinglabs.product;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.sql.ResultSet;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "products")
// @AllArgsConstructor  => bug with ResultSet constructor interaction
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Product {

  private String name;
  private String description;
  private double price;

  public Product(ResultSet rs) {
    try {
      this.name = rs.getString("name");
      this.description = rs.getString("description");
      this.price = rs.getDouble("price");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public Product(String name, String description, double price) {
    this.name = name;
    this.description = description;
    this.price = price;
  }

}

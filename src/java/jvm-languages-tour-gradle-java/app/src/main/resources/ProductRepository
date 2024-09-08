package tech.leafwinglabs.product;

import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

  JdbcTemplate jdbcTemplate;

  public ProductRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  private static final RowMapper<Product> productRowMapper = (rs, rowNum) ->
      new Product(rs.getNString(0),
                  rs.getNString(1),
                  rs.getDouble(2));

  public List<Product> getProducts() {
    return jdbcTemplate.query("SELECT * FROM product", productRowMapper);
  }

  public Product getProduct(Long id) {
    return jdbcTemplate.queryForObject("SELECT * FROM product WHERE id = ?",
                                       new Object[]{id},
                                       productRowMapper);
  }

  public Product createProduct(Product product) {
    jdbcTemplate.update("INSERT INTO product (name, description, price) VALUES (?, ?, ?)",
                        product.getName(),
                        product.getDescription(),
                        product.getPrice());
    return product;
  }

  public Product updateProduct(Long id, Product product) {
    jdbcTemplate.update("UPDATE product SET name = ?, description = ?, price = ? WHERE id = ?",
                        product.getName(),
                        product.getDescription(),
                        product.getPrice(),
                        id);
    return product;
  }

  public void deleteProduct(Long id) {
    jdbcTemplate.update("DELETE FROM product WHERE id = ?", id);
  }

}

package tech.leafwinglabs.product;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("/products")
public class ProductController {

  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @GetMapping
  public List<Product> getProducts() {
    return this.productService.getProducts();
  }

  @GetMapping("/{id}")
  public Product getProduct(@PathVariable Long id) {
    return this.productService.getProduct(id);
  }

  @PostMapping
  public Product createProduct(@RequestBody Product product) {
    return this.productService.createProduct(product);
  }

  @PutMapping("/{id}")
  public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
    return this.productService.updateProduct(id, product);
  }

  @DeleteMapping("/{id}")
  public void deleteProduct(@PathVariable Long id) {
    this.productService.deleteProduct(id);
  }

}

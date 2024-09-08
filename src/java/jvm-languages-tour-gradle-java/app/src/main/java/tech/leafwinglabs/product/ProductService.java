package tech.leafwinglabs.product;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

  private ProductRepository productRepository;

  public ProductService(ProductRepository productRepository) {
    this.productRepository = productRepository;
  }

  public List<Product> getProducts() {
    return productRepository.getProducts();
  }

  public Product getProduct(Long id) {
    return productRepository.getProduct(id);
  }

  public Product createProduct(Product product) {
    return productRepository.createProduct(product);
  }

  public Product updateProduct(Long id, Product product) {
    return productRepository.updateProduct(id, product);
  }

  public void deleteProduct(Long id) {
    productRepository.deleteProduct(id);
  }

}

package tech.leafwinglabs.product;

import java.time.Duration;
import java.util.List;

import reactor.core.Disposable;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

public class ProductStream {

  private ProductService productService;
  private CacheService cacheService;                                                // fallbacks
  private SuggestionService suggestionService;

  private ProductStream(ProductService productService, CacheService cacheService,
      SuggestionService suggestionService) {
    this.productService = productService;
    this.cacheService = cacheService;
    this.suggestionService = suggestionService;
  }

  public Mono<List<Product>> streamProducts() {                                     // LAZY STREAM
    return Mono.fromCallable(() -> productService.getProducts())
               .timeout(Duration.ofMillis(800))                              // time bound
               .onErrorResume(e -> Mono.just(cacheService.cachedProducts()))        // error fallback
               .switchIfEmpty(Mono.just(suggestionService.getSuggestions()))        // empty fallback
               .take(Duration.ofSeconds(5))                                 // time slice
               .publishOn(Schedulers.boundedElastic());                             // orchestration
  }

  public Disposable subscribeProducts() {
    return streamProducts().subscribe(UiUtils::show, UiUtils::errorPopup);          // ACTION
  }

}

  public static class CacheService {
    public List<Product> cachedProducts() {
      return List.of(new Product("Product 1", "Description 1", 1.0));
    }
  }

  public static class SuggestionService {
    public List<Product> getSuggestions() {
      return List.of(new Product("Product 6", "Description 6", 6.0));
    }
  }

  public static class UiUtils {

    public static void show(List<Product> products) {
      products.forEach(System.out::println);
    }

    public static void errorPopup(Throwable t) {
      System.err.println("Error: " + t.getMessage());
    }
  }

}

package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.service.ProductService;
import finalproject.jpnshop.web.dto.ResProduct;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/{productId}")
    public ResponseEntity<ResProduct.Response> getProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProduct(productId));
    }

    @PostMapping("/list")
    public ResponseEntity<List<ResProduct.Response>> getSimpleInfo(@RequestParam List<Long> productId) {
        return ResponseEntity.ok(productService.getSimpleInfo(productId));
    }

    @GetMapping
    public ResponseEntity<List<ResProduct.Response>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping("/save")
    public ResponseEntity<ResProduct.Response> saveProduct(@RequestParam Product product,
        Integer stock) {
        return ResponseEntity.ok(productService.saveProduct(product, stock));
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ResProduct.Response> updateProduct(@PathVariable Long productId,
        String productName, Integer price, Integer stock) {
        productService.updateProduct(productId, productName, price, stock);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ResProduct.Response> deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok().build();
    }
}

package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.properties.ErrorCode;
import finalproject.jpnshop.biz.exception.CustomException;
import finalproject.jpnshop.biz.repository.ProductRepository;
import finalproject.jpnshop.web.dto.ResProduct;
import finalproject.jpnshop.web.dto.ResProduct.Response;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Getter
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {

    private final ProductRepository productRepository;

    public List<ResProduct.Response> findProducts() {
        List<Product> products = productRepository.findAll();
        List<ResProduct.Response> productList = new ArrayList<>();
        for (Product product : products) {
            productList.add(ResProduct.Response.of(product));
        }
        return productList;
    }

    public Response findOne(Long productId) {
        return Response.of(productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND)));
    }

    @Transactional
    public void saveProduct(Product product, int stock) {
        if (product.getId() == null) {
            productRepository.save(product);
        } else {
            product.addStock(stock);
        }
    }

    @Transactional
    public void updateProduct(Long productId, String productName, int price, int stock) {
        Product product = productRepository.findById(productId).orElseThrow(
            () -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));
        product.setProductName(productName);
        product.setPrice(price);
        product.setStock(stock);
        productRepository.save(product);
    }

}

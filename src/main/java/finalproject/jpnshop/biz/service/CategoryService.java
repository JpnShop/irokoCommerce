package finalproject.jpnshop.biz.service;

import finalproject.jpnshop.biz.domain.Category;
import finalproject.jpnshop.biz.domain.Product;
import finalproject.jpnshop.biz.domain.SubCategory;
import finalproject.jpnshop.biz.repository.CategoryRepository;
import finalproject.jpnshop.biz.repository.ProductRepository;
import finalproject.jpnshop.biz.repository.SubCategoryRepository;
import finalproject.jpnshop.web.dto.ResProduct.Response;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;
    private final ProductRepository productRepository;

    public List<Category> getCategory() {
        return categoryRepository.findAll();
    }

    public List<Response> getProduct(String tags) {
        return productRepository.findByTagsContaining(tags)
            .stream().map(Response::simpleInfo)
            .collect(Collectors.toList());
    }
    

}

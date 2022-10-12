package finalproject.jpnshop.web.controller;

import finalproject.jpnshop.biz.domain.Category;
import finalproject.jpnshop.biz.repository.CategoryRepository;
import finalproject.jpnshop.biz.service.CategoryService;
import finalproject.jpnshop.web.dto.ResProduct.Response;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    @GetMapping("/category")
    public List<Category> getCategory() {
        return categoryService.getCategory();
    }

    @GetMapping("/tags")
    public List<Response> getProduct(@RequestBody String tag) {
        return categoryService.getProduct(tag);
    }

}

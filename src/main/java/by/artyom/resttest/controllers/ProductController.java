package by.artyom.resttest.controllers;

import by.artyom.resttest.dto.ProductDto;
import by.artyom.resttest.entities.Product;
import by.artyom.resttest.mappers.CustomProductDtoMapper;
import by.artyom.resttest.mappers.ProductMapper;
import by.artyom.resttest.repositories.ProductRepository;
import by.artyom.resttest.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(
//            @RequestHeader(name = "x-auth-token") String authToken,
            @RequestParam(name = "categoryId", required = false) Long categoryId
    ) {
        List<ProductDto> products;
        if (categoryId != null) {
            products = productService.getProductsByCategoryId(categoryId);
        }
        else {
            products = productService.getAllProducts();
        }

        return products;
    }

//    @GetMapping("/test")
//    public Product getProduct() {
//        return productRepository.findById(1L).orElse(null);
//    }

}

package by.artyom.resttest.services;

import by.artyom.resttest.dto.ProductDto;
import by.artyom.resttest.entities.Product;
import by.artyom.resttest.mappers.CustomProductDtoMapper;
import by.artyom.resttest.repositories.ProductRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProductService {
    private final ProductRepository productRepository;
    private final CustomProductDtoMapper customProductDtoMapper;

    @PersistenceContext
    private final EntityManager entityManager;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAllWithCategory().stream()
                .map(customProductDtoMapper::mapProductToDto)
                .toList();
    }

    public List<ProductDto> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId).stream()
                .map(customProductDtoMapper::mapProductToDto)
                .toList();
    }
}

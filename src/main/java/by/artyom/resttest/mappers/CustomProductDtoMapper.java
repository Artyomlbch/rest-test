package by.artyom.resttest.mappers;

import by.artyom.resttest.dto.CategoryDto;
import by.artyom.resttest.dto.ProductDto;
import by.artyom.resttest.entities.Product;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Component;

@Component
public class CustomProductDtoMapper {

    public ProductDto mapProductToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());

        productDto.setCategoryDto(new CategoryDto(product.getCategory().getId(),
                product.getCategory().getTitle()));

        return productDto;
    }

}

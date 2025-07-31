package by.artyom.resttest.mappers;

import by.artyom.resttest.dto.CategoryDto;
import by.artyom.resttest.dto.ProductDto;
import by.artyom.resttest.entities.Category;
import by.artyom.resttest.entities.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper {

//    @Mapping(source = "category.id", target = "categoryId")
//    ProductDto toDto(Product product);

    ProductDto productToProductDto(Product product);
    CategoryDto productToCategoryDto(Category category);
    Product toEntity(ProductDto productDto);

}

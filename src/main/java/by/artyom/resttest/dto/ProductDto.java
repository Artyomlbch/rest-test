package by.artyom.resttest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Long price;
    private CategoryDto categoryDto;

//    @JsonProperty("category")
//    private CategoryDto categoryDto;
}

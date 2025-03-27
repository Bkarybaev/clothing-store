package java16.clothingstore.dto.response;

import lombok.Data;

@Data
public class ProductResponse {
    private Long id;
    private String name;
    private String characteristic;
    private String category;
    private Double price;
}

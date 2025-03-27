package java16.clothingstore.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public class ProductRequest {
    private String name;
    private BigDecimal price;
    private String characteristic;
}

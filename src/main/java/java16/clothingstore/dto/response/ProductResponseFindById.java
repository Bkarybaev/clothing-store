package java16.clothingstore.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProductResponseFindById {
    private Long id;
    private String name;
    private String characteristic;
    private String category;
    private BigDecimal price;
    private List<CommentResponse> comments = new ArrayList<>();
    private int favourite;
}

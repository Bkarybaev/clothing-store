package java16.clothingstore.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BasketResponse {
    private List<ProductResponse> productResponse = new ArrayList<>();
}

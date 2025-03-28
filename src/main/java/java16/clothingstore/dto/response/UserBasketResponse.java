package java16.clothingstore.dto.response;

import lombok.Data;

@Data
public class UserBasketResponse {
    private Long id;
    private String firstName;
    private BasketResponse basketResponse;
}

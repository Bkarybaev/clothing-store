package java16.clothingstore.api;

import java16.clothingstore.dto.response.ProductResponse;
import java16.clothingstore.dto.response.ProductResponseFindById;
import java16.clothingstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductApi {
    private final ProductService productService;

    //  1. Бардык продуктыларды алып жатканда Категория жана
    //  прайс аркылуу фильтрация болуш керек жана прайс боюнча сортировка болуш керек

    @GetMapping("/getAllProducts")
    public List<?> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false, defaultValue = "0") Double minPrice,
            @RequestParam(required = false, defaultValue = "1000000") Double maxPrice,
            @RequestParam(required = false, defaultValue = "ASC") String sortOrder
    ) {
        return productService.findProducts(category, minPrice, maxPrice, sortOrder);
    }


    //    3 .Бир продуктыны алып жатканда комментарий кошо чыгыш керек
    //    4. Бир продукты алып жатканда канча лайк бар экенин чыгарыш керек
    @GetMapping("/findById/{id}")
    public ProductResponseFindById findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    //   5. User жактырган продуктысын избранныйга кошо алган жана
    //   ал жактан алып салган функционалы болсун
    @PostMapping("/saveFavorites/{id}/{userId}")
    public String saveFavorites(@PathVariable Long id, @PathVariable Long userId,
                                @RequestParam("favorite") Boolean favorite) {
        return productService.saveFavorites(id, favorite, userId);
    }

    //   6. Бир user’дин избранныйдагы продуктыларын алып чыккан функционалы болсун
    @GetMapping("/getAllFavoritesByUserId/{id}")
    public List<?> findAllFavoritesByUserId(@PathVariable Long id) {
        return productService.findAllFavoritesByUserId(id);
    }

}

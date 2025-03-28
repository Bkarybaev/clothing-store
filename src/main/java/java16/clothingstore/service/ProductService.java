package java16.clothingstore.service;

import java16.clothingstore.dto.response.ProductResponse;
import java16.clothingstore.dto.response.ProductResponseFindById;

import java.util.List;

public interface ProductService {
    List<?> findProducts(String category, Double minPrice, Double maxPrice, String sortOrder);

    ProductResponseFindById findById(Long id);

    String saveFavorites(Long id, Boolean favorite,Long userId);

    List<?> findAllFavoritesByUserId(Long id);

}

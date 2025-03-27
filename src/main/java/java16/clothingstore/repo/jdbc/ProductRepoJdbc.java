package java16.clothingstore.repo.jdbc;

import java16.clothingstore.dto.response.ProductResponse;
import java16.clothingstore.dto.response.ProductResponseFindById;

import java.util.List;

public interface ProductRepoJdbc {
    List<?> findProducts(String category, Double minPrice, Double maxPrice, String sortOrder);

    ProductResponseFindById findById(Long id);

    List<?> findAllFavoritesByUserId(Long id);
}

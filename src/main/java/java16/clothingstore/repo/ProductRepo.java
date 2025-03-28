package java16.clothingstore.repo;

import jakarta.transaction.Transactional;
import java16.clothingstore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.isFavorite = :favorite WHERE p.id = :id")
    void updateFavoriteProduct(@Param("id") Long id, @Param("favorite") Boolean favorite);

    default Product findByIdException(Long id){
        return findById(id).orElseThrow(()->new RuntimeException("Product not found"));
    }

}

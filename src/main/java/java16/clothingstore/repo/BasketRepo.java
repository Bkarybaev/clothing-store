package java16.clothingstore.repo;

import java16.clothingstore.models.Basket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BasketRepo extends JpaRepository<Basket, Long> {

    @Query("SELECT b FROM Basket b JOIN b.products p WHERE p.id = :productId AND b.user.id = :userId")
    Basket findByProductIdAndUserId(@Param("productId") Long productId, @Param("userId") Long userId);


    Basket findByUserId(Long id);
}

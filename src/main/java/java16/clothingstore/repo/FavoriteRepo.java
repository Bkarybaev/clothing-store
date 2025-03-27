package java16.clothingstore.repo;

import java16.clothingstore.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepo extends JpaRepository<Favorite, Long> {
    Favorite findByProductIdAndUserId(Long id, Long id1);
}

package java16.clothingstore.service.impl;

import jakarta.transaction.Transactional;
import java16.clothingstore.dto.response.ProductResponse;
import java16.clothingstore.dto.response.ProductResponseFindById;
import java16.clothingstore.models.Favorite;
import java16.clothingstore.models.Product;
import java16.clothingstore.models.User;
import java16.clothingstore.repo.FavoriteRepo;
import java16.clothingstore.repo.ProductRepo;
import java16.clothingstore.repo.UserRepo;
import java16.clothingstore.repo.jdbc.ProductRepoJdbc;
import java16.clothingstore.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepoJdbc productRepoJdbc;
    private final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final FavoriteRepo favoriteRepo;

    @Override
    public List<?> findProducts(String category, Double minPrice, Double maxPrice, String sortOrder) {
        return productRepoJdbc.findProducts(category, minPrice, maxPrice, sortOrder);
    }

    @Override
    public ProductResponseFindById findById(Long id) {
        return productRepoJdbc.findById(id);
    }

    @Override
    @Transactional
    public String saveFavorites(Long id, Boolean favorite, Long userId) {
        Product byId = productRepo.findById(id).orElse(null);
        User user = userRepo.findById(userId).orElse(null);

        if (byId != null) {
            productRepo.updateFavoriteProduct(byId.getId(), favorite);
            if (favorite) {
                if (user != null) {
                    Favorite r = favoriteRepo.findByProductIdAndUserId(byId.getId(), user.getId());
                    if (r == null) {
                        Favorite favo = new Favorite();
                        favo.setProduct(byId);
                        favo.setUser(user);
                        Favorite save = favoriteRepo.save(favo);
                        user.getFavorites().add(save);
                        byId.getFavorites().add(save);
                        return "success added favorite";
                    }
                    return "success added favorite";
                }
            } else {
                if (user != null) {
                    Favorite favo = favoriteRepo.findByProductIdAndUserId(byId.getId(), user.getId());
                    if (favo != null) {
                        user.getFavorites().remove(favo);
                        byId.getFavorites().remove(favo);
                        favoriteRepo.delete(favo);
                        return "success delete favorite";
                    }
                    return "success delete favorite";
                }
            }

        }
        return "not fount";
    }

    @Override
    public List<?> findAllFavoritesByUserId(Long id) {
        return productRepoJdbc.findAllFavoritesByUserId(id);
    }


}

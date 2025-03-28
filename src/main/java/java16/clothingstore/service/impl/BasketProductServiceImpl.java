package java16.clothingstore.service.impl;

import java16.clothingstore.models.Basket;
import java16.clothingstore.models.Product;
import java16.clothingstore.models.User;
import java16.clothingstore.repo.BasketRepo;
import java16.clothingstore.repo.ProductRepo;
import java16.clothingstore.repo.UserRepo;
import java16.clothingstore.service.BasketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class BasketProductServiceImpl implements BasketProductService {
    private final BasketRepo basketRepo;
    private final UserRepo userRepo;
    private final ProductRepo productRepo;

    @Override
    public String saveBasketRemoveBasket(Long pId, Long uId) {

        Basket basket = basketRepo.findByProductIdAndUserId(pId, uId);
        User user = userRepo.findByIdException(uId);
        Product product = productRepo.findByIdException(pId);


        if (basket == null) {
            Basket b_1  = basketRepo.findByUserId(user.getId());
            if (b_1 == null) {
                 b_1 = new Basket();
                b_1.setUser(user);
            }


            if (b_1.getProducts() == null || b_1.getProducts().isEmpty()) {
                b_1.setProducts(new ArrayList<>());
            }
            b_1.getProducts().add(product);
            Basket saveBasket = basketRepo.save(b_1);
            user.setBasket(saveBasket);
            userRepo.save(user);
            if (product.getBaskets() == null) {
                product.setBaskets(new ArrayList<>());
            }
            product.getBaskets().add(saveBasket);
            productRepo.save(product);
            return "success save";
        }


        if (product.getBaskets() != null && product.getBaskets().contains(basket)) {
            basket.getProducts().remove(product);
            product.getBaskets().removeIf(bas -> bas.getId().equals(basket.getId()));
            productRepo.save(product);
        }
        basketRepo.delete(basket);

        return "success delete basket";
    }
}

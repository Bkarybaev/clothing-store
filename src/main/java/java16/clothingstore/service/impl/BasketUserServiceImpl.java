package java16.clothingstore.service.impl;

import jakarta.transaction.Transactional;
import java16.clothingstore.dto.response.UserBasketResponse;
import java16.clothingstore.repo.BasketRepo;
import java16.clothingstore.repo.jdbc.BasketRepoJdbc;
import java16.clothingstore.service.BasketUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BasketUserServiceImpl implements BasketUserService {
    private final BasketRepoJdbc basketRepoJdbc;

    @Override
    public UserBasketResponse getBasketUserId() {
        return basketRepoJdbc.getBasketUserId();
    }
}

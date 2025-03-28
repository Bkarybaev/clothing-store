package java16.clothingstore.repo.jdbc.impl;

import java16.clothingstore.dto.response.ProductResponse;
import java16.clothingstore.dto.response.UserBasketResponse;
import java16.clothingstore.models.User;
import java16.clothingstore.repo.ProductRepo;
import java16.clothingstore.repo.UserRepo;
import java16.clothingstore.repo.jdbc.BasketRepoJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class BasketRepoJdbcImpl implements BasketRepoJdbc {
    private final JdbcTemplate jdbcTemplate;
    private final UserRepo userRepo;

    @Override
    public UserBasketResponse getBasketUserId() {
        User currentUser = userRepo.findCurrentUser();
        String sql = """
                select u.id, u.first_name  from baskets b
                join users u on u.id = b.user_id
                where b.user_id = ?
                """;
        UserBasketResponse query = jdbcTemplate.query(sql, new Object[]{currentUser.getId()}, rs -> {
            UserBasketResponse userBasketResponse = new UserBasketResponse();
            userBasketResponse.setId(rs.getLong("id"));
            userBasketResponse.setFirstName(rs.getString("first_name"));
//            userBasketResponse
//                    .getBasketResponse()
//                    .setProductResponse();

            return userBasketResponse;
        });

return query;
    }
}

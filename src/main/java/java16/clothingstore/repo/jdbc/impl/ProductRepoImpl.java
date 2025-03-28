package java16.clothingstore.repo.jdbc.impl;

import java16.clothingstore.dto.response.CommentResponse;
import java16.clothingstore.dto.response.ProductResponse;
import java16.clothingstore.dto.response.ProductResponseFindById;
import java16.clothingstore.repo.jdbc.ProductRepoJdbc;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepoImpl implements ProductRepoJdbc {
    private final JdbcTemplate jdbcTemplate;


    @Override
    public List<ProductResponse> findProducts(String category, Double minPrice, Double maxPrice, String sortOrder) {
        List<String> validSortOrders = List.of("ASC", "DESC");
        String orderBy = validSortOrders.contains(sortOrder.toUpperCase()) ? sortOrder.toUpperCase() : "ASC";

        String sql = """
                SELECT p.id AS productId, 
                       p.name, 
                       p.characteristic, 
                       p.category, 
                       p.price 
                FROM products p
                WHERE p.category = ?
                  AND p.price BETWEEN ? AND ?
                ORDER BY p.price """ + " " + orderBy;

        return jdbcTemplate.query(sql, new Object[]{category, minPrice, maxPrice}, (row, rowNum) -> {
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(row.getLong("productId"));
            productResponse.setName(row.getString("name"));
            productResponse.setCharacteristic(row.getString("characteristic"));
            productResponse.setCategory(row.getString("category"));
            productResponse.setPrice(row.getDouble("price"));
            return productResponse;
        });
    }

    @Override
    public ProductResponseFindById findById(Long id) {
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        String sql = """
        SELECT 
            p.id AS productId,
            p.name,
            p.characteristic,
            p.category,
            p.price,
            c.id AS commentId,
            c.comment,
            c.created_at,
            u.first_name,
            u.last_name,
            COUNT(distinct f.id) AS favoriteCount
        FROM products p
        LEFT JOIN comments c ON p.id = c.product_id
        LEFT JOIN users u ON u.id = c.user_id
         join favorites f ON p.id = f.product_id
        WHERE p.id = :productId
                        GROUP BY
            p.id, p.name, p.characteristic, p.category, p.price,
            c.id, c.comment, c.created_at,
            u.first_name, u.last_name;
        """;

        MapSqlParameterSource params = new MapSqlParameterSource("productId", id);

        List<ProductResponseFindById> productList = namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
            ProductResponseFindById product = new ProductResponseFindById();
            product.setId(rs.getLong("productId"));
            product.setName(rs.getString("name"));
            product.setCharacteristic(rs.getString("characteristic"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getBigDecimal("price"));
            product.setFavourite(rs.getInt("favoriteCount"));

            return product;
        });

        if (productList.isEmpty()) {
            return null;
//           throw new RuntimeException("Product with id " + id + " not found");
        }

        ProductResponseFindById productResponseFindById = productList.getFirst();
        if (productResponseFindById.getComments() == null) {
            productResponseFindById.setComments(new ArrayList<>());
        }


        namedParameterJdbcTemplate.query(sql, params, (rs) -> {
            if (rs.getObject("commentId") != null) {
                CommentResponse commentResponse = new CommentResponse();
                commentResponse.setId(rs.getLong("commentId"));
                commentResponse.setText(rs.getString("comment"));
                commentResponse.setDate(rs.getDate("created_at") != null ? rs.getDate("created_at").toLocalDate() : null);
                commentResponse.setAuthor(rs.getString("first_name") + " " + rs.getString("last_name"));

                productResponseFindById.getComments().add(commentResponse);
            }
        });

        return productResponseFindById;
    }

    @Override
    public List<?> findAllFavoritesByUserId(Long id) {
        String sql = """
            SELECT p.id AS productId,
                   p.name,
                   p.characteristic,
                   p.category,
                   p.price
            FROM products p
            JOIN favorites f ON p.id = f.product_id
            WHERE f.user_id = :id
            """;

        MapSqlParameterSource params = new MapSqlParameterSource("id", id);
        NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);

        return namedParameterJdbcTemplate.query(sql, params, (rs, rowNum) -> {
            ProductResponse product = new ProductResponse();
            product.setId(rs.getLong("productId"));
            product.setName(rs.getString("name"));
            product.setCharacteristic(rs.getString("characteristic"));
            product.setCategory(rs.getString("category"));
            product.setPrice(rs.getDouble("price"));
            return product;
        });
    }
}

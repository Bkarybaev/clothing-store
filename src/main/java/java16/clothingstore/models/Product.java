package java16.clothingstore.models;

import jakarta.persistence.*;
import java16.clothingstore.enums.Category;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_product")
    @SequenceGenerator(name = "gen_product", allocationSize = 1,initialValue = 100)
    private Long id;

    private String name;
    private BigDecimal price;
    private String characteristic;
    private Boolean isFavorite;
    private String madel;
    @Enumerated(EnumType.STRING)
    private Category category;


    @OneToMany(mappedBy = "product")
    private List<Image> images = new ArrayList<>();

    @ManyToOne
    private Brand brand;

    @ManyToMany(mappedBy = "products")
    @ToString.Exclude
    private List<Basket> baskets = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Favorite> favorites = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    private List<Comment> comments = new ArrayList<>();

}

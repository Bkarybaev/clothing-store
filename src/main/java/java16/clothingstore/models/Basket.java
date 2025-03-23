package java16.clothingstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen_baskets")
    @SequenceGenerator(name = "gen_baskets",allocationSize = 1,initialValue = 100)
    private Long id;
    @ManyToMany
    private List<Product> products;
    @OneToOne
    private User user;

}

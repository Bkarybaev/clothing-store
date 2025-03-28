package java16.clothingstore.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baskets")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen_baskets")
    @SequenceGenerator(name = "gen_baskets",allocationSize = 1,initialValue = 100)
    private Long id;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @ToString.Exclude
    private List<Product> products = new ArrayList<>();
    @OneToOne(cascade = CascadeType.PERSIST)
    private User user;


}

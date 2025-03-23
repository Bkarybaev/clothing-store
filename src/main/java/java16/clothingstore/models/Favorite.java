package java16.clothingstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "favorites")
public class Favorite {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen_favorites")
    @SequenceGenerator(name = "gen_favorites",allocationSize = 1,initialValue = 100)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;
}

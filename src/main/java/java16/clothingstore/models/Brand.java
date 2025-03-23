package java16.clothingstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brands")
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "gen_brand")
    @SequenceGenerator(name = "gen_brand",allocationSize = 1,initialValue = 100)
    private Long id;
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @OneToMany(mappedBy = "brand")
    private List<Product> products = new ArrayList<>();


}

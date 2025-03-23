package java16.clothingstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "images")
@NoArgsConstructor
@AllArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_image")
    @SequenceGenerator(name = "gen_image",allocationSize = 1,initialValue = 100)
    private Long id;
    private String imageUrl;
    @ManyToOne
    private Product product;

    @OneToOne
    private Brand brand;
}

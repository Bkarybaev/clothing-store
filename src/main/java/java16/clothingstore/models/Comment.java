package java16.clothingstore.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_comment")
    @SequenceGenerator(name = "gen_comment", allocationSize = 1, initialValue = 100)
    private Long id;
    private String comment;
    private LocalDate createdAt;
    @ManyToOne
    private Product product;
    @ManyToOne
    private User user;
}

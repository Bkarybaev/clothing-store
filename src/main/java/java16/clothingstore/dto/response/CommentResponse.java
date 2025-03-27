package java16.clothingstore.dto.response;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CommentResponse {
    private Long id;
    private String text;
    private LocalDate date;
    private String author;
}

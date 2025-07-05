package bmis.com.bmis.models.dtos;

import lombok.Data;
import bmis.com.bmis.models.Book;
import jakarta.validation.constraints.*;

@Data
public class BookDto {

    private Long id;

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Author cannot be empty")
    private String author;

    private Long publisherId;

    @NotNull(message = "Genre is required")
    @Positive(message = "Genre must be a valid selection")
    private Long genreId;

      // Constructor that maps from entity
    public BookDto(Book book) {
        this.id          = book.getId();
        this.title       = book.getTitle();
        this.author      = book.getAuthor();
        this.genreId     = book.getGenre().getId();
        this.publisherId = book.getPublisher().getId();
       
    }

    public static BookDto fromEntity(Book book) {
        return new BookDto(book);
    }

}

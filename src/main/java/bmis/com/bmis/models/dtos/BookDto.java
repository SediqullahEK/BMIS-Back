package bmis.com.bmis.models.dtos;

import lombok.Data;
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

}

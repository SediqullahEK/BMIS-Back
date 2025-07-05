package bmis.com.bmis.models.dtos;

import bmis.com.bmis.models.Genre;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class GenreDto {
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    // Constructor that maps from entity
    public GenreDto(Genre book) {
        this.id          = book.getId();
        this.name       = book.getName();
    }

    public static GenreDto fromEntity(Genre genre) {
        return new GenreDto(genre);
    }
}

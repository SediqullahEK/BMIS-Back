package bmis.com.bmis.models.dtos;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class GenreDto {
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;
}

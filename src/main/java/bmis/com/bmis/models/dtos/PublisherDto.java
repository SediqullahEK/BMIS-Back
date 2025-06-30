package bmis.com.bmis.models.dtos;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PublisherDto {
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    private String address;
}

package bmis.com.bmis.models.dtos;

import bmis.com.bmis.models.Genre;
import bmis.com.bmis.models.Publisher;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class PublisherDto {
    private Long id;

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    private String address;

    public PublisherDto(Publisher publisher) {
        this.id          = publisher.getId();
        this.name       = publisher.getName();
        this.address       = publisher.getAddress();
    }

    public static PublisherDto fromEntity(Publisher publisher) {
        return new PublisherDto(publisher);
    }
}

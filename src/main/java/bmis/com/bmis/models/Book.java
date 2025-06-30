package bmis.com.bmis.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data  
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String author;

    @ManyToOne 
    @JoinColumn(name = "genre_id")
    private Genre genre;

    @ManyToOne 
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;
}

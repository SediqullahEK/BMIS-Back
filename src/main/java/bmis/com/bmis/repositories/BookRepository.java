package bmis.com.bmis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bmis.com.bmis.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findByTitleAndAuthor(String title, String authorId);
}
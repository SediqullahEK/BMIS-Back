package bmis.com.bmis.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import bmis.com.bmis.models.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    public Book findByTitleAndAuthor(String title, String authorId);

    @Query(value = "select * from books WHERE department_id=?1  AND (deleted=false AND active=true) ORDER BY semester_id ASC", nativeQuery = true)
    public Optional<List<Book>> findByDepartmentId(Long Department);
}
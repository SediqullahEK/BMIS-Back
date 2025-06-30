package bmis.com.bmis.services;

import java.util.List;
import java.util.Optional;

import bmis.com.bmis.models.Book;
import bmis.com.bmis.models.dtos.BookDto;

public interface BookService {
    List<Book> findAll();
    Optional<Book> findById(Long id);
    Book save(BookDto bookDto);
    void deleteById(Long id);
    Boolean checkIfExists(BookDto bookDto);
    BookDto findDtoById(Long id);
}

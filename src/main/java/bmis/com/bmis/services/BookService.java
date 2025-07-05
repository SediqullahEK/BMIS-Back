package bmis.com.bmis.services;

import java.util.List;
import java.util.Map;

import bmis.com.bmis.models.Book;
import bmis.com.bmis.models.dtos.BookDto;

public interface BookService {
    List<BookDto> findAll();
    Book findById(Long id);
    Map<String, Object> save(BookDto bookDto);
    Boolean deleteById(Long id);
    Boolean checkIfExists(BookDto bookDto);
    BookDto findDtoById(Long id);
}

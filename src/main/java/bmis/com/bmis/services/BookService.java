package bmis.com.bmis.services;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bmis.com.bmis.models.Book;
import bmis.com.bmis.models.dtos.BookDto;

public interface BookService {
    Page<BookDto> findAll(Pageable pageable);
    Book findById(Long id);
    BookDto save(BookDto bookDto);
    Boolean deleteById(Long id);
    Boolean checkIfExists(BookDto bookDto);
    BookDto findDtoById(Long id);
}

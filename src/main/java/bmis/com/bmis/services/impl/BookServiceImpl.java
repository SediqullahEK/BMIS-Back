package bmis.com.bmis.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bmis.com.bmis.models.Book;
import bmis.com.bmis.models.Genre;
import bmis.com.bmis.models.Publisher;
import bmis.com.bmis.models.dtos.BookDto;
import bmis.com.bmis.repositories.BookRepository;
import bmis.com.bmis.services.BookService;
import bmis.com.bmis.services.GenreService;
import bmis.com.bmis.services.PublisherService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class BookServiceImpl implements BookService { 

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private GenreService genreService; 
    @Autowired
    private PublisherService publisherService;

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    @Override
    public Book save(BookDto bookDto) {
        Book book = (bookDto.getId() != null)
                ? bookRepository.findById(bookDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Book not found: " + bookDto.getId()))
                : new Book();

        book.setTitle(bookDto.getTitle());
        book.setAuthor(bookDto.getAuthor());

        if (bookDto.getPublisherId() != null) {
            Publisher pub = publisherService.findById(bookDto.getPublisherId())
                    .orElseThrow(() -> new EntityNotFoundException("Publisher not found: " + bookDto.getPublisherId()));
            book.setPublisher(pub);
        } else {
            book.setPublisher(null);
        }

        if (bookDto.getGenreId() != null) {
            Genre genre = genreService.findById(bookDto.getGenreId())
                    .orElseThrow(() -> new EntityNotFoundException("Genre not found: " + bookDto.getGenreId()));
            book.setGenre(genre);
        } else {
            book.setGenre(null);
        }

        return bookRepository.save(book);
    }

    @Override
    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExists(BookDto bookDto) {
        Book book = bookRepository.findByTitleAndAuthor(bookDto.getTitle(), bookDto.getAuthor());
        if (book != null) {
            return true;
        } else {
            return false;
        }
    }
    
    @Override
    public BookDto findDtoById(Long id) {
        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));

        BookDto dto = new BookDto();
        dto.setId(    book.getId());
        dto.setTitle( book.getTitle());
        dto.setAuthor(book.getAuthor());
        dto.setGenreId( book.getGenre()     != null ? book.getGenre().getId()     : null);
        dto.setPublisherId(
            book.getPublisher() != null ? book.getPublisher().getId() : null
        );
        return dto;
    }

}

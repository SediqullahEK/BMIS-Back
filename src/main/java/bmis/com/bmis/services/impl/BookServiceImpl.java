package bmis.com.bmis.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public Page<BookDto> findAll(Pageable pageable) {

        Page<Book> bookPage = bookRepository.findAll(pageable);

        return bookPage.map(book -> new BookDto(book));
        // Or more concisely: return bookPage.map(BookDto::new);
    }

    @Override
    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with id " + id));
    }

    @Override
    public BookDto save(BookDto bookDto) {
        if (checkIfExists(bookDto) && bookDto.getId() == null) {
            throw new IllegalStateException("Book already exists");
        } else {
            Book book = (bookDto.getId() != null)
                    ? bookRepository.findById(bookDto.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Book not found: " + bookDto.getId()))
                    : new Book();

            book.setTitle(bookDto.getTitle());
            book.setAuthor(bookDto.getAuthor());

            if (bookDto.getPublisherId() != null) {
                Publisher pub = publisherService.findById(bookDto.getPublisherId())
                        .orElseThrow(
                                () -> new EntityNotFoundException("Publisher not found: " + bookDto.getPublisherId()));
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

            BookDto newDto = new BookDto(bookRepository.save(book));
            return newDto;
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));

        bookRepository.deleteById(book.getId());
        return true;
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
        return new BookDto(book);

    }

}

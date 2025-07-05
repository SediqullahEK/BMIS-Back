// package BMIS.com.BMIS;

// import static org.junit.jupiter.api.Assertions.*;
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.ArgumentMatchers.argThat;
// import static org.mockito.Mockito.*;

// import java.util.Arrays;
// import java.util.List;
// import java.util.Optional;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.junit.jupiter.MockitoExtension;

// import bmis.com.bmis.models.Book;
// import bmis.com.bmis.models.Genre;
// import bmis.com.bmis.models.Publisher;
// import bmis.com.bmis.models.dtos.BookDto;
// import bmis.com.bmis.models.dtos.GenreDto;
// import bmis.com.bmis.models.dtos.PublisherDto;
// import bmis.com.bmis.repositories.BookRepository;
// import bmis.com.bmis.repositories.GenreRepository;
// import bmis.com.bmis.repositories.PublisherRepository;
// import bmis.com.bmis.services.impl.BookServiceImpl;
// import bmis.com.bmis.services.impl.GenreServiceImpl;
// import bmis.com.bmis.services.impl.PublisherServiceImpl;

// @ExtendWith(MockitoExtension.class)
// public class BMISServicesImpTest {

//     @Mock
//     private BookRepository bookRepository;
//     @Mock
//     private GenreRepository genreRepository;
//     @Mock
//     private PublisherRepository publisherRepository;

//     @Mock
//     private bmis.com.bmis.services.GenreService genreService;
//     @Mock
//     private bmis.com.bmis.services.PublisherService publisherService;

//     @InjectMocks
//     private BookServiceImpl bookService;
//     @InjectMocks
//     private GenreServiceImpl genreServiceImpl;
//     @InjectMocks
//     private PublisherServiceImpl publisherServiceImpl;

//     private Genre testGenre;
//     private Publisher testPublisher;
//     private Book testBook;
//     private GenreDto testGenreDto;
//     private PublisherDto testPublisherDto;
//     private BookDto testBookDto;

//     @BeforeEach
//     void setUp() {
//         testGenre = new Genre(1L, "Fiction");
//         testPublisher = new Publisher(1L, "Penguin", "NY");
//         testBook = new Book();
//         testBook.setId(1L);
//         testBook.setTitle("Title");
//         testBook.setAuthor("Author");
//         testBook.setGenre(testGenre);
//         testBook.setPublisher(testPublisher);

//         testGenreDto = new GenreDto();
//         testGenreDto.setId(1L);
//         testGenreDto.setName("Fiction");

//         testPublisherDto = new PublisherDto();
//         testPublisherDto.setId(1L);
//         testPublisherDto.setName("Penguin");
//         testPublisherDto.setAddress("NY");

//         // testBookDto = new BookDto();
//         // testBookDto.setId(1L);
//         // testBookDto.setTitle("Title");
//         // testBookDto.setAuthor("Author");
//         // testBookDto.setGenreId(1L);
//         // testBookDto.setPublisherId(1L);
//     }

//     // GenreService Tests
//     @Test
//     void testFindAllGenres() {
//         when(genreRepository.findAll()).thenReturn(Arrays.asList(testGenre));
//         List<Genre> genres = genreServiceImpl.findAll();
//         assertEquals(1, genres.size());
//         verify(genreRepository).findAll();
//     }

//     @Test
//     void testFindGenreById() {
//         when(genreRepository.findById(1L)).thenReturn(Optional.of(testGenre));
//         Optional<Genre> result = genreServiceImpl.findById(1L);
//         assertTrue(result.isPresent());
//         assertEquals("Fiction", result.get().getName());
//     }

//     @Test
//     void testSaveNewGenre() {
//         when(genreRepository.save(any(Genre.class))).thenReturn(testGenre);
//         GenreDto dto = new GenreDto();
//         dto.setName("Fiction");
//         Genre saved = genreServiceImpl.save(dto);
//         assertEquals("Fiction", saved.getName());
//     }

//     @Test
//     void testCheckIfGenreExists() {
//         when(genreRepository.findByName("Fiction")).thenReturn(testGenre);
//         assertTrue(genreServiceImpl.checkIfExists(testGenreDto));
//     }

//     @Test
//     void testFindGenreDtoById() {
//         when(genreRepository.findById(1L)).thenReturn(Optional.of(testGenre));
//         GenreDto dto = genreServiceImpl.findDtoById(1L);
//         assertEquals(1L, dto.getId());
//         assertEquals("Fiction", dto.getName());
//     }

//     // PublisherService Tests
//     @Test
//     void testFindAllPublishers() {
//         when(publisherRepository.findAll()).thenReturn(Arrays.asList(testPublisher));
//         List<Publisher> pubs = publisherServiceImpl.findAll();
//         assertEquals(1, pubs.size());
//     }

//     @Test
//     void testFindPublisherById() {
//         when(publisherRepository.findById(1L)).thenReturn(Optional.of(testPublisher));
//         Optional<Publisher> result = publisherServiceImpl.findById(1L);
//         assertTrue(result.isPresent());
//     }

//     @Test
//     void testSaveNewPublisher() {
//         when(publisherRepository.save(any(Publisher.class))).thenReturn(testPublisher);
//         PublisherDto dto = new PublisherDto();
//         dto.setName("Penguin");
//         dto.setAddress("NY");
//         Publisher saved = publisherServiceImpl.save(dto);
//         assertEquals("Penguin", saved.getName());
//     }

//     @Test
//     void testCheckIfPublisherExists() {
//         when(publisherRepository.findByName("Penguin")).thenReturn(testPublisher);
//         assertTrue(publisherServiceImpl.checkIfExists(testPublisherDto));
//     }

//     @Test
//     void testFindPublisherDtoById() {
//         when(publisherRepository.findById(1L)).thenReturn(Optional.of(testPublisher));
//         PublisherDto dto = publisherServiceImpl.findDtoById(1L);
//         assertEquals(1L, dto.getId());
//         assertEquals("NY", dto.getAddress());
//     }

//     // BookService Tests
//     // @Test
//     // void testFindAllBooks() {
//     //     when(bookRepository.findAll()).thenReturn(Arrays.asList(testBook));
//     //     List<Book> books = bookService.findAll();
//     //     assertEquals(1, books.size());
//     // }

//     // @Test
//     // void testFindBookById() {
//     //     when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
//     //     Optional<Book> result = bookService.findById(1L);
//     //     assertTrue(result.isPresent());
//     // }

//     // @Test
//     // void testSaveNewBook() {
//     //     BookDto dto = new BookDto();
//     //     dto.setTitle("New");
//     //     dto.setAuthor("A");
//     //     dto.setGenreId(1L);
//     //     dto.setPublisherId(1L);
//     //     when(genreService.findById(1L)).thenReturn(Optional.of(testGenre));
//     //     when(publisherService.findById(1L)).thenReturn(Optional.of(testPublisher));
//     //     when(bookRepository.save(any(Book.class))).thenReturn(testBook);
//     //     Book saved = bookService.save(dto);
//     //     assertEquals("Title", saved.getTitle()); // from testBook
//     //     verify(bookRepository).save(any(Book.class));
//     // }

//     @Test
//     void testCheckIfBookExists() {
//         when(bookRepository.findByTitleAndAuthor("Title", "Author")).thenReturn(testBook);
//         assertTrue(bookService.checkIfExists(testBookDto));
//     }

//     @Test
//     void testFindBookDtoById() {
//         when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
//         BookDto dto = bookService.findDtoById(1L);
//         assertEquals(1L, dto.getId());
//         assertEquals("Author", dto.getAuthor());
//     }

//     @Test
//     void testDeleteBook() {
//         doNothing().when(bookRepository).deleteById(1L);
//         bookService.deleteById(1L);
//         verify(bookRepository).deleteById(1L);
//     }

//     @Test
//     void testFindGenreById_whenNotFound() {
//         when(genreRepository.findById(99L)).thenReturn(Optional.empty());
//         Optional<Genre> result = genreServiceImpl.findById(99L);
//         assertFalse(result.isPresent());
//     }

   
//     @Test
//     void testUpdateExistingGenre() {
//         Genre existingGenre = new Genre(1L, "Old Fiction");
//         GenreDto dtoToUpdate = new GenreDto();
//         dtoToUpdate.setId(1L);
//         dtoToUpdate.setName("Updated Fiction");

//         when(genreRepository.findById(1L)).thenReturn(Optional.of(existingGenre));
//         when(genreRepository.save(any(Genre.class))).thenAnswer(invocation -> invocation.getArgument(0)); 
                                                                                                       

//         Genre updated = genreServiceImpl.save(dtoToUpdate); 

//         assertEquals(1L, updated.getId());
//         assertEquals("Updated Fiction", updated.getName());
//         verify(genreRepository).findById(1L);
//         verify(genreRepository)
//                 .save(argThat(genre -> genre.getId().equals(1L) && genre.getName().equals("Updated Fiction")));
//     }
// }

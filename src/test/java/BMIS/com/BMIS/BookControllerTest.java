// package BMIS.com.BMIS; // Match your main package structure

// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// import java.util.Arrays;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.test.web.servlet.MockMvc;

// import bmis.com.bmis.controllers.BookController;
// import bmis.com.bmis.models.Book;
// import org.springframework.test.context.bean.override.mockito.MockitoBean;
// import bmis.com.bmis.models.dtos.PublisherDto;
// import bmis.com.bmis.models.Genre;
// import bmis.com.bmis.models.Publisher;
// import bmis.com.bmis.models.dtos.BookDto;
// import bmis.com.bmis.services.PublisherService;
// import bmis.com.bmis.models.dtos.GenreDto;
// import bmis.com.bmis.services.BookService;
// import bmis.com.bmis.services.GenreService;

// @WebMvcTest(BookController.class)
// public class BookControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockitoBean
//     private BookService bookService;

//     @MockitoBean
//     private GenreService genreService;

//     @MockitoBean
//     private PublisherService publisherService;

//     private Genre testGenre;
//     private Publisher testPublisher;
//     private Book testBook;
//     private GenreDto testGenreDto;
//     private PublisherDto testPublisherDto;
//     private BookDto testBookDto;

//     @BeforeEach
//     public void setUp() {
//         testGenre      = new Genre(1L, "Fiction");
//         testPublisher  = new Publisher(1L, "Test Publisher", null);
//         testBook       = new Book();
//         testBook.setId(1L);
//         testBook.setTitle("A Title");
//         testBook.setAuthor("An Author");
//         testBook.setGenre(testGenre);
//         testBook.setPublisher(testPublisher);

//         testGenreDto     = new GenreDto();
//         testGenreDto.setId(1L);
//         testGenreDto.setName("Fiction");

//         testPublisherDto = new PublisherDto();
//         testPublisherDto.setId(1L);
//         testPublisherDto.setName("Test Publisher");
//         testPublisherDto.setAddress(null);

//         // testBookDto      = new BookDto();
//         // testBookDto.setId(1L);
//         // testBookDto.setTitle("A Title");
//         // testBookDto.setAuthor("An Author");
//         // testBookDto.setGenreId(1L);
//         // testBookDto.setPublisherId(1L);
//     }

//     // @Test
//     // public void testGetAllBooks() throws Exception {
//     //     when(bookService.findAll()).thenReturn(Arrays.asList(testBook));

//     //     mockMvc.perform(get("/books/list"))
//     //            .andExpect(model().attribute("books", Arrays.asList(testBook)))
//     //            .andExpect(status().isOk())
//     //            .andExpect(view().name("books/list"))
//     //            .andExpect(model().attributeExists("books"))
//     //           ;
//     // }

//     @Test
//     public void testShowAddNewBookForm() throws Exception {
//         when(genreService.findAll()).thenReturn(Arrays.asList(testGenre));
//         when(publisherService.findAll()).thenReturn(Arrays.asList(testPublisher));

//         mockMvc.perform(get("/books/add"))
//                 .andExpect(status().isOk())
//                 .andExpect(view().name("books/create"))
//                 .andExpect(model().attributeExists("genres"))
//                 .andExpect(model().attributeExists("publishers"))
//                 .andExpect(model().attributeExists("bookDto"))
//                 .andExpect(model().attribute("pageTitle", "Add new Book"));
//     }

//     @Test
//     public void testStoreBook_success() throws Exception {
//         when(bookService.checkIfExists(any(BookDto.class))).thenReturn(false);

//         mockMvc.perform(post("/books/store")
//                         .flashAttr("bookDto", testBookDto))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/books/list"));

//         verify(bookService, times(1)).checkIfExists(any(BookDto.class));
//         verify(bookService, times(1)).save(any(BookDto.class));
//     }

//     @Test
//     public void testStoreBookWhenBookExists_shouldReturnCreateForm() throws Exception {
//         when(bookService.checkIfExists(any(BookDto.class))).thenReturn(true);
//         when(genreService.findAll()).thenReturn(Arrays.asList(testGenre));
//         when(publisherService.findAll()).thenReturn(Arrays.asList(testPublisher));

//         mockMvc.perform(post("/books/store")
//                         .flashAttr("bookDto", testBookDto))
//                 .andExpect(view().name("books/create"))
//                 .andExpect(model().attributeExists("genres"))
//                 .andExpect(model().attributeExists("publishers"))
//                 .andExpect(status().isOk())
//                 .andExpect(model().attributeHasFieldErrors("bookDto", "title"));


//         verify(bookService, times(1)).checkIfExists(any(BookDto.class));
//         verify(bookService, never()).save(any(BookDto.class));
//     }

// }
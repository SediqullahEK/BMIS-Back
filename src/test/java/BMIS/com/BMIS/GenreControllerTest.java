// package BMIS.com.BMIS;

// import bmis.com.bmis.controllers.GenreController;
// import bmis.com.bmis.models.Genre;
// import bmis.com.bmis.models.dtos.GenreDto;
// import bmis.com.bmis.services.GenreService;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.test.context.bean.override.mockito.MockitoBean; 
// import org.springframework.test.web.servlet.MockMvc;
// import java.util.Arrays;
// import java.util.List;
// import static org.hamcrest.Matchers.*; 
// import static org.mockito.ArgumentMatchers.any;
// import static org.mockito.Mockito.*;
// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// @WebMvcTest(GenreController.class) 
// public class GenreControllerTest {

//     @Autowired
//     private MockMvc mockMvc;

//     @MockitoBean 
//     private GenreService genreService;

//     private GenreDto testGenreDto;
//     private List<Genre> testGenreList;

//     @BeforeEach
//     void setUp() {

//         testGenreDto = new GenreDto();
//         testGenreDto.setId(1L);
//         testGenreDto.setName("Fiction");

//         testGenreList = Arrays.asList(
//                 new Genre(1L, "Fiction"),
//                 new Genre(2L, "Science Fiction")
//         );
//     }

//     @Test
//     void listGenresshouldReturnListViewWithGenres() throws Exception {
//         when(genreService.findAll()).thenReturn(testGenreList);

//         mockMvc.perform(get("/genres/list"))
//                 .andExpect(status().isOk())
//                 .andExpect(view().name("genres/list"))
//                 .andExpect(model().attributeExists("genres"))
//                 .andExpect(model().attribute("genres", hasSize(2)))
//                 .andExpect(model().attribute("genres", testGenreList));
//     }

//     @Test
//     void addGenreshouldReturnCreateViewWithNewDto() throws Exception {
    
//         mockMvc.perform(get("/genres/add"))
//                 .andExpect(status().isOk())
//                 .andExpect(view().name("genres/create"))
//                 .andExpect(model().attributeExists("genreDto"))
//                 .andExpect(model().attribute("genreDto", instanceOf(GenreDto.class)));
//     }

//     @Test
//     void storeGenrewhenValidAndNotExists_shouldSaveAndRedirect() throws Exception {
     
//         when(genreService.checkIfExists(any(GenreDto.class))).thenReturn(false);
//         mockMvc.perform(post("/genres/store")
//                         .flashAttr("genreDto", testGenreDto)) 
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/genres/list"));

//         verify(genreService, times(1)).checkIfExists(any(GenreDto.class));
//         verify(genreService, times(1)).save(any(GenreDto.class));
//     }

//     @Test
//     void storeGenrewhenGenreAlreadyExists_shouldReturnCreateViewWithError() throws Exception {
    
//         when(genreService.checkIfExists(any(GenreDto.class))).thenReturn(true);
      
//         mockMvc.perform(post("/genres/store")
//                         .flashAttr("genreDto", testGenreDto))
//                 .andExpect(status().isOk())
//                 .andExpect(view().name("genres/create"))
//                 .andExpect(model().attributeHasFieldErrors("genreDto", "name"));

//         verify(genreService, times(1)).checkIfExists(any(GenreDto.class));
//         verify(genreService, never()).save(any(GenreDto.class)); 
//     }

//     @Test
//     void storeGenrewhenBindingResultHasErrors_shouldReturnCreateView() throws Exception {
      
//         GenreDto invalidDto = new GenreDto(); 
//         invalidDto.setId(3L); 

//         when(genreService.checkIfExists(invalidDto)).thenReturn(false); 
//         when(genreService.findAll()).thenReturn(testGenreList);

//         mockMvc.perform(post("/genres/store")
//                         .flashAttr("genreDto", invalidDto)) 
//                 .andExpect(view().name("genres/create"))
//                 .andExpect(model().attribute("genres", testGenreList))
//                 .andExpect(status().isOk()); 

//         verify(genreService, times(1)).checkIfExists(invalidDto); 
//         verify(genreService, never()).save(any(GenreDto.class)); 
//         verify(genreService, times(1)).findAll();
//     }


//     @Test
//     void editGenrewhenGenreExists_shouldReturnEditViewWithDto() throws Exception {
      
//         Long genreId = 1L;
//         when(genreService.findDtoById(genreId)).thenReturn(testGenreDto);

//         mockMvc.perform(get("/genres/edit").param("id", genreId.toString()))
//                 .andExpect(view().name("genres/edit"))
//                 .andExpect(model().attributeExists("genreDto"))
//                 .andExpect(model().attribute("genreDto", testGenreDto))
//                 .andExpect(status().isOk());

//         verify(genreService, times(1)).findDtoById(genreId);
//     }

//     @Test
//     void updateGenrewhenValid_shouldSaveAndRedirect() throws Exception {
     
//         mockMvc.perform(post("/genres/update")
//                         .flashAttr("genreDto", testGenreDto))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/genres/list"));

//         verify(genreService, times(1)).save(any(GenreDto.class));
//     }

//     @Test
//     void updateGenre_whenBindingResultHasErrors_shouldReturnEditView() throws Exception {
       
//         GenreDto invalidDto = new GenreDto(); 
//         invalidDto.setId(testGenreDto.getId()); 

//         mockMvc.perform(post("/genres/update")
//                         .flashAttr("genreDto", invalidDto))
//                 .andExpect(status().isOk())
//                 .andExpect(view().name("genres/edit"));

//         verify(genreService, never()).save(any(GenreDto.class));
//     }

//     @Test
//     void deleteGenreshouldCallServiceDeleteAndRedirect() throws Exception {
       
//         Long genreId = 1L;
//         doNothing().when(genreService).deleteById(genreId); 

//         mockMvc.perform(get("/genres/delete").param("id", genreId.toString()))
//                 .andExpect(status().is3xxRedirection())
//                 .andExpect(redirectedUrl("/genres/list"));

//         verify(genreService, times(1)).deleteById(genreId);
//     }
// }

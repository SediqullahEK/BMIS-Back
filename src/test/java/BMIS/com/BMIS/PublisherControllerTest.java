package BMIS.com.BMIS;
import bmis.com.bmis.controllers.PublisherController;
import bmis.com.bmis.models.Publisher;
import bmis.com.bmis.models.dtos.PublisherDto;
import bmis.com.bmis.services.PublisherService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PublisherController.class) 
public class PublisherControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PublisherService publisherService;

    private PublisherDto testPublisherDto;
   
    private List<Publisher> testPublisherList;

    @BeforeEach
    void setUp() {
   
        testPublisherDto = new PublisherDto();
        testPublisherDto.setId(1L);
        testPublisherDto.setName("Penguin Books");
        testPublisherDto.setAddress("New York, NY");

        testPublisherList = Arrays.asList(
                new Publisher(1L, "Penguin Books", "New York, NY"),
                new Publisher(2L, "HarperCollins", "London, UK")
        );
    }

    @Test
    void listPublishersshouldReturnListViewWithPublishers() throws Exception {
        when(publisherService.findAll()).thenReturn(testPublisherList);

        mockMvc.perform(get("/publishers/list"))
                .andExpect(model().attributeExists("publishers"))
                .andExpect(model().attribute("publishers", testPublisherList))
                .andExpect(status().isOk());
    }

    @Test
    void addPublishershouldReturnCreateViewWithNewDto() throws Exception {
    
        mockMvc.perform(get("/publishers/add"))
                .andExpect(status().isOk())
                .andExpect(view().name("publishers/create"))
                .andExpect(model().attributeExists("publisherDto"))
                .andExpect(model().attribute("publisherDto", instanceOf(PublisherDto.class)));
    }

    @Test
    void storePublisherwhenValidAndNotExists_shouldSaveAndRedirect() throws Exception {
      
        when(publisherService.checkIfExists(any(PublisherDto.class))).thenReturn(false);

        mockMvc.perform(post("/publishers/store")
                        .flashAttr("publisherDto", testPublisherDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/publishers/list"));

        verify(publisherService, times(1)).checkIfExists(any(PublisherDto.class));
        verify(publisherService, times(1)).save(any(PublisherDto.class));
    }

    @Test
    void storePublisherwhenPublisherAlreadyExists_shouldReturnCreateViewWithError() throws Exception {
     
        when(publisherService.checkIfExists(any(PublisherDto.class))).thenReturn(true);
        
        when(publisherService.findAll()).thenReturn(testPublisherList);

        mockMvc.perform(post("/publishers/store")
                        .flashAttr("publisherDto", testPublisherDto))
                .andExpect(status().isOk())
                .andExpect(view().name("publishers/create"))
                .andExpect(model().attributeHasFieldErrors("publisherDto", "name"))
                .andExpect(model().attribute("publishers", testPublisherList)); 

        verify(publisherService, times(1)).checkIfExists(any(PublisherDto.class));
        verify(publisherService, never()).save(any(PublisherDto.class));
        verify(publisherService, times(1)).findAll();
    }

    @Test
    void storePublisher_whenBindingResultHasErrors_shouldReturnCreateView() throws Exception {
      
        PublisherDto invalidDto = new PublisherDto(); 
        invalidDto.setId(3L);

        when(publisherService.checkIfExists(invalidDto)).thenReturn(false);
        when(publisherService.findAll()).thenReturn(testPublisherList); 

        mockMvc.perform(post("/publishers/store")
                        .flashAttr("publisherDto", invalidDto))
                .andExpect(status().isOk())
                .andExpect(view().name("publishers/create"))
                .andExpect(model().attributeHasErrors("publisherDto"))
                .andExpect(model().attribute("publishers", testPublisherList));

        verify(publisherService, times(1)).checkIfExists(invalidDto);
        verify(publisherService, never()).save(any(PublisherDto.class));
        verify(publisherService, times(1)).findAll();
    }


    @Test
    void editPublisher_whenPublisherExists_shouldReturnEditViewWithDtoAndPageTitle() throws Exception {
     
        Long publisherId = 1L;
        when(publisherService.findDtoById(publisherId)).thenReturn(testPublisherDto);

      
        mockMvc.perform(get("/publishers/edit").param("id", publisherId.toString()))
                .andExpect(status().isOk())
                .andExpect(view().name("publishers/edit"))
                .andExpect(model().attributeExists("publisherDto"))
                .andExpect(model().attribute("publisherDto", testPublisherDto));

        verify(publisherService, times(1)).findDtoById(publisherId);
    }


    @Test
    void updatePublisher_whenValid_shouldSaveAndRedirect() throws Exception {

        mockMvc.perform(post("/publishers/update")
                        .flashAttr("publisherDto", testPublisherDto))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/publishers/list"));

        verify(publisherService, times(1)).save(any(PublisherDto.class));
    }

    @Test
    void deletePublisher_shouldCallServiceDeleteAndRedirect() throws Exception {
        // Arrange
        Long publisherId = 1L;
        doNothing().when(publisherService).deleteById(publisherId);

        // Act & Assert
        mockMvc.perform(get("/publishers/delete").param("id", publisherId.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/publishers/list"));

        verify(publisherService, times(1)).deleteById(publisherId);
    }
}
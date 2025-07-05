package bmis.com.bmis.controllers;

import bmis.com.bmis.models.dtos.BookDto;
import bmis.com.bmis.services.BookService;
import bmis.com.bmis.services.GenreService;
import bmis.com.bmis.services.PublisherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private GenreService genreService;
    @Autowired
    private PublisherService publisherService;

    @GetMapping("/list")
    public List<BookDto> home() {
        return bookService.findAll();
    }

    @PostMapping("/store")
    public ResponseEntity<Map<String, Object>> storeBook(@RequestBody String userRequest) throws Exception {
        if (userRequest == null || userRequest.isEmpty()) {
            throw new Exception("Invalid request data");
        } else {
            Gson g = new Gson();
            BookDto bookDto = g.fromJson(userRequest, BookDto.class);
            if (bookDto == null) {
                throw new Exception("Invalid book data");
            } else {
                bookService.save(bookDto);
            }
        }
        

        return ResponseEntity.ok(null);
    }

    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long id, Model model, HttpServletRequest request) {

        BookDto bookDto = bookService.findDtoById(id);

        model.addAttribute("bookDto", bookDto);
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("pageTitle", "Edit Book");

        return "books/edit";
    }

    @PostMapping("/update")
    public String updateBook(@Valid @ModelAttribute BookDto bookDto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("publishers", publisherService.findAll());
            return "books/edit";
        }
        bookService.save(bookDto);
        return "redirect:/books/list";
    }

    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id) {
        bookService.deleteById(id);
        return "redirect:/books/list";
    }
}
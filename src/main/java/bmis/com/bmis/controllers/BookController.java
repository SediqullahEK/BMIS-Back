package bmis.com.bmis.controllers;

import bmis.com.bmis.models.dtos.BookDto;
import bmis.com.bmis.services.BookService;
import bmis.com.bmis.services.GenreService;
import bmis.com.bmis.services.PublisherService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService; 
    @Autowired
    private GenreService genreService;
    @Autowired
    private PublisherService publisherService; 
    
    @GetMapping("/list")
    public String home(Model model, HttpServletRequest request) {
        model.addAttribute("books", bookService.findAll());
        return "books/list";
    }

    @GetMapping("/add")
    public String addBook(Model model, HttpServletRequest request) {
        model.addAttribute("genres", genreService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("bookDto", new BookDto());
        model.addAttribute("pageTitle",  "Add new Book");
        return "books/create";
    }

    @PostMapping("/store")
    public String storeBook(@Valid @ModelAttribute BookDto bookDto, BindingResult result, Model model) {
        if (bookService.checkIfExists(bookDto)) {
            result.addError(new FieldError("bookDto", "title", "This Book already exists"));
        }
        if (result.hasErrors()) {
            model.addAttribute("genres", genreService.findAll());
            model.addAttribute("publishers", publisherService.findAll());

            return "books/create";
        }
        bookService.save(bookDto);
       
        return "redirect:/books/list";
    }

  
    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long id, Model model, HttpServletRequest request) {
   
        BookDto bookDto = bookService.findDtoById(id);

        model.addAttribute("bookDto",   bookDto);
        model.addAttribute("genres",     genreService.findAll());
        model.addAttribute("publishers", publisherService.findAll());
        model.addAttribute("pageTitle",  "Edit Book");

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
package bmis.com.bmis.controllers;

import bmis.com.bmis.models.dtos.BookDto;
import bmis.com.bmis.services.BookService;

import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public ResponseEntity<Page<BookDto>> listBooks(Pageable pageable) {

        Page<BookDto> bookPage = bookService.findAll(pageable);

        return ResponseEntity.ok(bookPage);
    }

    @PostMapping("/store")
    public ResponseEntity<BookDto> storeBook(@RequestBody String userRequest) throws Exception {

        if (userRequest == null || userRequest.isEmpty()) {
            throw new Exception("Invalid request data");
        } else {
            Gson g = new Gson();
            BookDto bookDto = g.fromJson(userRequest, BookDto.class);
            if (bookDto == null) {
                throw new Exception("Invalid book data");
            } else {

                return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDto));
            }
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<BookDto> updateBook(@RequestBody String userRequest, @PathVariable Long id) throws Exception {
        if (userRequest == null || userRequest.isEmpty()) {
            throw new Exception("Invalid request data");
        }
        Gson g = new Gson();
        BookDto bookDto = g.fromJson(userRequest, BookDto.class);
        if (bookDto == null) {
            throw new Exception("Invalid book data");
        }
        bookDto.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.save(bookDto));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteBook(@PathVariable Long id) {
        
       return ResponseEntity.status(HttpStatus.CREATED).body(bookService.deleteById(id));
    }
}
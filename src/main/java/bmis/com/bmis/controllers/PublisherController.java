package bmis.com.bmis.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

import bmis.com.bmis.models.dtos.GenreDto;
import bmis.com.bmis.models.dtos.PublisherDto;
import bmis.com.bmis.services.PublisherService;
@RestController
@RequestMapping("/api/publishers")
public class PublisherController {

    @Autowired
    private PublisherService publisherService;

    
    @GetMapping("/all")
    public ResponseEntity<List<PublisherDto>> allGenres() {

        List<PublisherDto> publishers = publisherService.findAll();

        return ResponseEntity.ok(publishers);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<PublisherDto>> listGenres(Pageable pageable) {

        Page<PublisherDto> genrePage = publisherService.listAll(pageable);

        return ResponseEntity.ok(genrePage);
    }

    @PostMapping("/store")
    public ResponseEntity<PublisherDto> storeGenre(@RequestBody String userRequest) throws Exception {

        if (userRequest == null || userRequest.isEmpty()) {
            throw new Exception("Invalid request data");
        } else {
            Gson g = new Gson();
            PublisherDto PublisherDto = g.fromJson(userRequest, PublisherDto.class);
            if (PublisherDto == null) {
                throw new Exception("Invalid genre data");
            } else {

                return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.save(PublisherDto));
            }
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PublisherDto> updateGenre(@RequestBody String userRequest, @PathVariable Long id) throws Exception {
        if (userRequest == null || userRequest.isEmpty()) {
            throw new Exception("Invalid request data");
        }
        Gson g = new Gson();
        PublisherDto PublisherDto = g.fromJson(userRequest, PublisherDto.class);
        if (PublisherDto == null) {
            throw new Exception("Invalid genre data");
        }
        PublisherDto.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.save(PublisherDto));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteGenre(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.CREATED).body(publisherService.deleteById(id));
    }

}
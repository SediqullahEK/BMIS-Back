package bmis.com.bmis.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.google.gson.Gson;

import bmis.com.bmis.models.dtos.GenreDto;
import bmis.com.bmis.services.GenreService;

@RestController
@RequestMapping("/api/genres")
public class GenreController {

    @Autowired
    private GenreService genreService;

    @GetMapping("/list")
    public ResponseEntity<Page<GenreDto>> listGenres(Pageable pageable) {

        Page<GenreDto> genrePage = genreService.findAll(pageable);

        return ResponseEntity.ok(genrePage);
    }

    @PostMapping("/store")
    public ResponseEntity<GenreDto> storeGenre(@RequestBody String userRequest) throws Exception {

        if (userRequest == null || userRequest.isEmpty()) {
            throw new Exception("Invalid request data");
        } else {
            Gson g = new Gson();
            GenreDto genreDto = g.fromJson(userRequest, GenreDto.class);
            if (genreDto == null) {
                throw new Exception("Invalid genre data");
            } else {

                return ResponseEntity.status(HttpStatus.CREATED).body(genreService.save(genreDto));
            }
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<GenreDto> updateGenre(@RequestBody String userRequest, @PathVariable Long id) throws Exception {
        if (userRequest == null || userRequest.isEmpty()) {
            throw new Exception("Invalid request data");
        }
        Gson g = new Gson();
        GenreDto genreDto = g.fromJson(userRequest, GenreDto.class);
        if (genreDto == null) {
            throw new Exception("Invalid genre data");
        }
        genreDto.setId(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.save(genreDto));
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteGenre(@PathVariable Long id) {

        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.deleteById(id));
    }
}
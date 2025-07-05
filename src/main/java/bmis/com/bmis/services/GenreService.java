package bmis.com.bmis.services;


import java.util.List;
import java.util.Optional;

import bmis.com.bmis.models.Genre;
import bmis.com.bmis.models.dtos.GenreDto;

public interface GenreService {
    List<GenreDto> findAll();
    Optional<Genre> findById(Long id);
    Genre save(GenreDto genreDto);
    void deleteById(Long id);
    Boolean checkIfExists(GenreDto genreDto);
    GenreDto findDtoById(Long id);
}
package bmis.com.bmis.services;


import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bmis.com.bmis.models.Genre;
import bmis.com.bmis.models.dtos.GenreDto;

public interface GenreService {
    Page<GenreDto> findAll(Pageable pageable);
    Optional<Genre> findById(Long id);
    GenreDto save(GenreDto genreDto);
    Boolean deleteById(Long id);
    Boolean checkIfExists(GenreDto genreDto);
    GenreDto findDtoById(Long id);
}
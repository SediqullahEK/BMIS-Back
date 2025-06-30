package bmis.com.bmis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bmis.com.bmis.models.Genre;
import bmis.com.bmis.models.dtos.GenreDto;
import bmis.com.bmis.repositories.GenreRepository;
import bmis.com.bmis.services.GenreService;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Optional;

@Service 
public class GenreServiceImpl implements GenreService { 

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override 
    public Genre save(GenreDto genreDto) {
         Genre genre = (genreDto.getId() != null)
                ? genreRepository.findById(genreDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Book not found: " + genreDto.getId()))
                : new Genre();

        genre.setName(genreDto.getName());

        return genreRepository.save(genre);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }

    @Override
    public Boolean checkIfExists(GenreDto genreDto) {
        Genre genre = genreRepository.findByName(genreDto.getName());
        if (genre != null) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public GenreDto findDtoById(Long id) {
        Genre genre = genreRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Genre not found: " + id));

        GenreDto dto = new GenreDto();
        dto.setId(    genre.getId());
        dto.setName( genre.getName());
        return dto;
    }
}
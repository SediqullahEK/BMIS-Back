package bmis.com.bmis.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bmis.com.bmis.models.Book;
import bmis.com.bmis.models.Genre;
import bmis.com.bmis.models.dtos.GenreDto;
import bmis.com.bmis.repositories.GenreRepository;
import bmis.com.bmis.services.GenreService;
import jakarta.persistence.EntityNotFoundException;

import java.util.Optional;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public Page<GenreDto> findAll(Pageable pageable) {

        Page<Genre> genrPage = genreRepository.findAll(pageable);

        return genrPage.map(genre -> new GenreDto(genre));
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return genreRepository.findById(id);
    }

    @Override
    public GenreDto save(GenreDto genreDto) {
        if (checkIfExists(genreDto) && genreDto.getId() == null) {
            throw new IllegalStateException("Genre already exists");
        } else {
            Genre genre = (genreDto.getId() != null)
                    ? genreRepository.findById(genreDto.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Genre not found: " + genreDto.getId()))
                    : new Genre();

            genre.setName(genreDto.getName());

            GenreDto newDto = new GenreDto(genreRepository.save(genre));
            return newDto;
        }
    }

    @Override
    public Boolean deleteById(Long id) {
        Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre not found: " + id));

        genreRepository.deleteById(genre.getId());
        return true;
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

        GenreDto dto = new GenreDto(genre);
        return dto;
    }
}
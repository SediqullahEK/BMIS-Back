package bmis.com.bmis.services;

import java.util.Optional;

import bmis.com.bmis.models.Publisher;
import bmis.com.bmis.models.dtos.PublisherDto;

public interface PublisherService {
    Iterable<Publisher> findAll();
    Optional<Publisher> findById(Long id);
    Publisher save(PublisherDto publisherDto);
    void deleteById(Long id);
    Boolean checkIfExists(PublisherDto genreDto);
    PublisherDto findDtoById(Long id);
}

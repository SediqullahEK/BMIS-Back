package bmis.com.bmis.services;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import bmis.com.bmis.models.Publisher;
import bmis.com.bmis.models.dtos.PublisherDto;

public interface PublisherService {
    List<PublisherDto>  findAll();
    Page<PublisherDto>  listAll(Pageable pageable);
    Optional<Publisher> findById(Long id);
    PublisherDto save(PublisherDto publisherDto);
    Boolean deleteById(Long id);
    Boolean checkIfExists(PublisherDto genreDto);
    PublisherDto findDtoById(Long id);
}

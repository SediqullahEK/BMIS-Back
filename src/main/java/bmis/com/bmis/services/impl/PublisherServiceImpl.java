package bmis.com.bmis.services.impl;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import bmis.com.bmis.models.Genre;
import bmis.com.bmis.models.Publisher;
import bmis.com.bmis.models.dtos.GenreDto;
import bmis.com.bmis.models.dtos.PublisherDto;
import bmis.com.bmis.repositories.PublisherRepository;
import bmis.com.bmis.services.PublisherService;
import jakarta.persistence.EntityNotFoundException;

@Service
public class PublisherServiceImpl implements PublisherService { 

     @Autowired
    private PublisherRepository publisherRepository;

     @Override
    public List<PublisherDto> findAll() {

        List<Publisher> publishers = publisherRepository.findAll();

       return publishers.stream()
                .map(publisher -> new PublisherDto(publisher))
                .collect(Collectors.toList());

    }
    @Override
    public Page<PublisherDto> listAll(Pageable pageable) {

        Page<Publisher> publisherPage = publisherRepository.findAll(pageable);

        return publisherPage.map(publisher -> new PublisherDto(publisher));
    }


    @Override     
    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    @Override
    public PublisherDto save(PublisherDto publisherDto) {
        if (checkIfExists(publisherDto) && publisherDto.getId() == null) {
            throw new IllegalStateException("publisher already exists");
        } else {
            Publisher publisher = (publisherDto.getId() != null)
                    ? publisherRepository.findById(publisherDto.getId())
                            .orElseThrow(() -> new EntityNotFoundException("Genre not found: " + publisherDto.getId()))
                    : new Publisher();

            publisher.setName(publisherDto.getName());

            PublisherDto newDto = new PublisherDto(publisherRepository.save(publisher));
            return newDto;
        }
    }


    @Override
    public Boolean deleteById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("publisher not found: " + id));

        publisherRepository.deleteById(publisher.getId());
        return true;
    }

    @Override
    public Boolean checkIfExists(PublisherDto publisherDto) {
        Publisher publisher = publisherRepository.findByName(publisherDto.getName());
        if (publisher != null) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public PublisherDto findDtoById(Long id) {
        Publisher publisher = publisherRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Genre not found: " + id));

        PublisherDto dto = new PublisherDto(publisher);
        
        return dto;
    }
}
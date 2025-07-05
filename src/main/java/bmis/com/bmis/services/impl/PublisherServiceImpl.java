package bmis.com.bmis.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bmis.com.bmis.models.Publisher;
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
         return publisherRepository.findAll().stream()
                .map(PublisherDto::new)
                .collect(Collectors.toList());
    }

    @Override     
    public Optional<Publisher> findById(Long id) {
        return publisherRepository.findById(id);
    }

    @Override 
    public Publisher save(PublisherDto publisherDto) {
         Publisher publisher = (publisherDto.getId() != null)
                ? publisherRepository.findById(publisherDto.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Book not found: " + publisherDto.getId()))
                : new Publisher();

        publisher.setName(publisherDto.getName());
        publisher.setAddress(publisherDto.getAddress());

        return publisherRepository.save(publisher);
    }

    @Override
    public void deleteById(Long id) {
        publisherRepository.deleteById(id);
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
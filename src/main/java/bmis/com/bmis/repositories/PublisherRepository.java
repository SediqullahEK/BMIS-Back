package bmis.com.bmis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bmis.com.bmis.models.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    public Publisher findByName(String name);
}
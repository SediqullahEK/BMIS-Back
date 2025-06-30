package bmis.com.bmis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bmis.com.bmis.models.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {
    public Genre findByName(String name);
}
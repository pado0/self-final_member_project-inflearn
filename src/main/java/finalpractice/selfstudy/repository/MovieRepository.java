package finalpractice.selfstudy.repository;

import finalpractice.selfstudy.entity.item.Album;
import finalpractice.selfstudy.entity.item.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}

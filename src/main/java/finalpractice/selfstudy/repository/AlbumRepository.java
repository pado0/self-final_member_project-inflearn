package finalpractice.selfstudy.repository;

import finalpractice.selfstudy.entity.item.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}

package finalpractice.selfstudy.repository;

import finalpractice.selfstudy.entity.item.Album;
import finalpractice.selfstudy.entity.item.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}

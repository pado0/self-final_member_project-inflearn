package finalpractice.selfstudy.service;

import finalpractice.selfstudy.dto.ItemPostDto;
import finalpractice.selfstudy.entity.item.Album;
import finalpractice.selfstudy.entity.item.Book;
import finalpractice.selfstudy.entity.item.Movie;
import finalpractice.selfstudy.repository.AlbumRepository;
import finalpractice.selfstudy.repository.BookRepository;
import finalpractice.selfstudy.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {

    private final AlbumRepository albumRepository; // final 안써줘서 주입이 안되고있엇따.
    private final BookRepository bookRepository;
    private final MovieRepository movieRepository;

    @Transactional
    public Long joinAlbum(Album album){
        Album savedAlbum = albumRepository.save(album);
        return savedAlbum.getId();
    }

    public Optional<Album> findAlbum(Long id) {
        System.out.println("ItemService.findAlbum");
        return albumRepository.findById(id);
    }

    // todo : category 정리하기, get() null일 경우 exception처리 https://www.latera.kr/blog/2019-07-02-effective-optional/
    @Transactional
    public void updateAlbum(Long id, ItemPostDto.AlbumDto albumDto){
        Optional<Album> findAlbum = findAlbum(id);

        // transaction 안에서 변경감지로 수행
        findAlbum.get().setName(albumDto.getName());
        findAlbum.get().setPrice(albumDto.getPrice());
        findAlbum.get().setEtc(albumDto.getEtc());
        findAlbum.get().setArtist(albumDto.getArtist());
        findAlbum.get().setStockQuantity(albumDto.getStockQuantity());
    }

    @Transactional
    public Long joinBook(Book book){
        Book savedBook = bookRepository.save(book);
        return savedBook.getId();
    }


    @Transactional
    public Long joinMovie(Movie movie){
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie.getId();
    }


    public List<Album> findAllAlbum(){
        return albumRepository.findAll();
    }

    public List<Book> findAllBook(){
        return bookRepository.findAll();
    }

    public List<Movie> findAllMovie(){
        return movieRepository.findAll();
    }


}

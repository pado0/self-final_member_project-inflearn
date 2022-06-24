package finalpractice.selfstudy.controller;

import finalpractice.selfstudy.dto.ItemGetDto;
import finalpractice.selfstudy.dto.ItemPostDto;
import finalpractice.selfstudy.entity.item.Album;
import finalpractice.selfstudy.entity.item.Book;
import finalpractice.selfstudy.entity.item.Movie;
import finalpractice.selfstudy.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    // todo : item 하위 객체들의 repository와 service 리펙토링 할 수 있지 않을까 https://jjeda.tistory.com/8
    // todo: 객체 Dto를 entity로 변환하는 것을 좀더 편하게 칠 수 있지 않을까
    @PostMapping("/item/album")
    public void joinAlbumItem(@RequestBody @Valid ItemPostDto.AlbumDto albumDto) {

        Album album = new Album();
        album.setName(albumDto.getName());
        album.setPrice(albumDto.getPrice());
        album.setStockQuantity(albumDto.getStockQuantity());

        // todo: category 구조와 어떤식으로 받을지 생각해보기.
        /*List<Category> categories =
                albumDto.getCategory_name().stream().map(
                        c -> new Category()
                )*/
        //album.setCategories(albumDto.getCategory_name().get(0)); // 카테고리 리스트 루프 돌려야하는지 확인
        album.setArtist(albumDto.getArtist());
        album.setEtc(albumDto.getEtc());

        itemService.joinAlbum(album);
    }

    @PostMapping("/item/book")
    public void joinBookItem(@RequestBody @Valid ItemPostDto.BookDto bookDto) {

        Book book = new Book();
        book.setName(bookDto.getName());
        book.setPrice(bookDto.getPrice());
        book.setStockQuantity(bookDto.getStockQuantity());
        //album.setCategories(albumDto.getCategories()); // 카테고리 리스트 루프 돌려야하는지 확인
        book.setAuthor(bookDto.getAuthor());
        book.setIsbn(bookDto.getIsbn());

        itemService.joinBook(book);
    }

    @PutMapping("/item/album/{id}")
    public void updateAlbumItem(@RequestBody @Valid ItemPostDto.AlbumDto albumDto,
                                @PathVariable Long id) {
        // 트랜잭션 안에서 set 수행해야함.
        itemService.updateAlbum(id, albumDto);
    }

    // todo : 이게 맞나 싶다.. 뜯어넣기... 세 객체를 하나의 리스트로 리턴하는
    @GetMapping("/item")
    public List<ItemGetDto> getItemList() {

        List<Album> allAlbum = itemService.findAllAlbum();
        for (Album album : allAlbum) {
            System.out.println("album = " + album);
        }
        List<ItemGetDto> itemGetDtos = new ArrayList<>();

        allAlbum.stream().forEach(a -> itemGetDtos.add(
                new ItemGetDto(a.getName(), a.getPrice(), a.getStockQuantity(), null,
                        a.getArtist(), a.getEtc(), null, null, null, null)));

        List<Book> allBook = itemService.findAllBook();
        allBook.stream().forEach(b -> itemGetDtos.add(
                new ItemGetDto(b.getName(), b.getPrice(), b.getStockQuantity(), null, null, null, b.getAuthor(), b.getIsbn(), null, null)));


        List<Movie> allMovie = itemService.findAllMovie();
        allMovie.stream().forEach(b -> itemGetDtos.add(
                new ItemGetDto(b.getName(), b.getPrice(), b.getStockQuantity(), null, null, null, null, null, b.getDirector(), b.getActor())));


        return itemGetDtos;

    }

}

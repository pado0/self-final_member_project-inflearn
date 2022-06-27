package finalpractice.selfstudy.controller;

import finalpractice.selfstudy.dto.ItemGetDto;
import finalpractice.selfstudy.dto.ItemPostDto;
import finalpractice.selfstudy.entity.item.Album;
import finalpractice.selfstudy.entity.item.Book;
import finalpractice.selfstudy.entity.item.Movie;
import finalpractice.selfstudy.service.CategoryService;
import finalpractice.selfstudy.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;

    // todo : item 하위 객체들의 repository와 service 리펙토링 할 수 있지 않을까 https://jjeda.tistory.com/8
    // todo: 객체 Dto를 entity로 변환하는 것을 좀더 편하게 칠 수 있지 않을까
    @PostMapping("/item/album")
    public ResponseEntity<?> joinAlbumItem(@RequestBody @Valid ItemPostDto.AlbumDto albumDto, BindingResult bindingResult) {


        // 오류가 있을 경우 오류 리턴 코드
        if(bindingResult.hasErrors()){
            ObjectError objectError = bindingResult.getAllErrors().stream().findFirst().get();
            return ResponseEntity.badRequest().body(objectError.getDefaultMessage());
        }

        Album album = new Album();
        album.setName(albumDto.getName());
        album.setPrice(albumDto.getPrice());
        album.setStockQuantity(albumDto.getStockQuantity());

        // todo: category 구조와 어떤식으로 받을지 생각해보기.
        album.setCategory(categoryService.findById(albumDto.getCategoryId()).get());
        album.setArtist(albumDto.getArtist());
        album.setEtc(albumDto.getEtc());

        itemService.joinAlbum(album);

        return ResponseEntity.ok("item album saved");
    }

    @PostMapping("/item/book")
    public void joinBookItem(@RequestBody @Valid ItemPostDto.BookDto bookDto) {

        Book book = new Book();
        book.setName(bookDto.getName());
        book.setPrice(bookDto.getPrice());
        book.setStockQuantity(bookDto.getStockQuantity());
        // 카테고리 id로 받아온다. //todo: optional null 처리. 카테고리 id로 카테고리를 받아와 객체에 세팅하는 중
        book.setCategory(categoryService.findById(bookDto.getCategoryId()).get());
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

        List<ItemGetDto> itemGetDtos = new ArrayList<>();
        List<Album> allAlbum = itemService.findAllAlbum();
        List<Book> allBook = itemService.findAllBook();
        List<Movie> allMovie = itemService.findAllMovie();



        // 뜯어넣기
        allAlbum.stream().forEach(x -> itemGetDtos.add(new ItemGetDto(
                x.getName(), x.getPrice(), x.getStockQuantity(), x.getCategory().getId()
                , x.getArtist(), x.getEtc(), null, null, null, null
        )));

        allBook.stream().forEach(x -> itemGetDtos.add(new ItemGetDto(
                x.getName(), x.getPrice(), x.getStockQuantity(), x.getCategory().getId()
                , null, null, x.getAuthor(), x.getIsbn(), null, null
        )));

        allMovie.stream().forEach(x -> itemGetDtos.add(new ItemGetDto(
                x.getName(), x.getPrice(), x.getStockQuantity(), x.getCategory().getId()
                , null, null, null, null, x.getDirector(), x.getActor()
        )));


        return itemGetDtos;
    }

}

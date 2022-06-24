package finalpractice.selfstudy;

import finalpractice.selfstudy.entity.item.Album;
import finalpractice.selfstudy.repository.AlbumRepository;
import finalpractice.selfstudy.service.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class ItemServiceTest {

    @Autowired
    ItemService itemService;

    @Autowired
    AlbumRepository albumRepository;
    
    @Test
    public void AlbumJoinRepositoryTest() {
        // given
        Album album = new Album();
        album.setName("hyo");
        album.setStockQuantity(1);
        album.setEtc("etc");
        album.setPrice(1000);
        
        // when
        Album savedAlbum = albumRepository.save(album);
        Album findAlbum = albumRepository.findAll().get(0);

        // then
        assertThat(savedAlbum.getName()).isEqualTo(findAlbum.getName());
        System.out.println("findAlbum.getName() = " + findAlbum.getName());
    }

    @Test
    public void AlbumServiceJoinTest() {
        // given
        Album album = new Album();
        album.setName("hyo");
        album.setStockQuantity(1);
        album.setEtc("etc");
        album.setPrice(1000);

        // when
        Long savedAlbumId = itemService.joinAlbum(album);
        Optional<Album> findMember = itemService.findAlbum(savedAlbumId);

        // then
        assertThat(findMember.get().getName()).isEqualTo(album.getName());
    }
}

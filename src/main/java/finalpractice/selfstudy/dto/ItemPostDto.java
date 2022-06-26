package finalpractice.selfstudy.dto;

import finalpractice.selfstudy.entity.Category;
import lombok.Data;
import org.junit.experimental.categories.Categories;

import java.util.List;

@Data
public class ItemPostDto {

    @Data
    public static class AlbumDto{
        private String name;
        private int price;
        private int stockQuantity;
        private Long categoryId;
        private String artist;
        private String etc;
    }

    @Data
    public static class BookDto{
        private String name;
        private int price;
        private int stockQuantity;
        private Long categoryId;
        private String author;
        private String isbn;
    }

    @Data
    public static class MovieDto{
        private String name;
        private int price;
        private int stockQuantity;
        private Long categoryId;
        private String director;
        private String actor;
    }
}

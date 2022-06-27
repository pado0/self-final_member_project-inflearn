package finalpractice.selfstudy.dto;

import finalpractice.selfstudy.entity.Category;
import finalpractice.selfstudy.validation.ItemPrice;
import lombok.Data;
import org.junit.experimental.categories.Categories;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class ItemPostDto {

    @Data
    public static class AlbumDto{
        @NotBlank
        private String name;

        @ItemPrice
        private int price;
        private int stockQuantity;
        private Long categoryId;
        private String artist;
        private String etc;
    }

    @Data
    public static class BookDto{

        @NotBlank
        private String name;

        @ItemPrice
        private int price;
        private int stockQuantity;
        private Long categoryId;
        private String author;
        private String isbn;
    }

    @Data
    public static class MovieDto{
        @NotBlank
        private String name;

        @ItemPrice // custom validation
        private int price;
        private int stockQuantity;
        private Long categoryId;
        private String director;
        private String actor;
    }
}

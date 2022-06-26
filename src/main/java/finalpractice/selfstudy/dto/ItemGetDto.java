package finalpractice.selfstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ItemGetDto {
    private String name;
    private int price;
    private int stockQuantity;
    private Long categoryId;

    private String artist;
    private String etc;

    private String author;
    private String isbn;

    private String director;
    private String actor;

}

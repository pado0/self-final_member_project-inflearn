package finalpractice.selfstudy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
public class ItemGetDto {
    private String name;
    private int price;
    private int stockQuantity;
    private List<String> category_name;

    private String artist;
    private String etc;

    private String author;
    private String isbn;

    private String director;
    private String actor;

    public ItemGetDto(String name, int price, int stockQuantity, List<String> category_name, String artist, String etc, String author, String isbn, String director, String actor) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category_name = category_name;
        this.artist = artist;
        this.etc = etc;
        this.author = author;
        this.isbn = isbn;
        this.director = director;
        this.actor = actor;
    }
}

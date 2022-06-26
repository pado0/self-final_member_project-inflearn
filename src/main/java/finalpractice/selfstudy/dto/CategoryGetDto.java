package finalpractice.selfstudy.dto;

import finalpractice.selfstudy.entity.Category;
import finalpractice.selfstudy.entity.item.Item;

import java.util.List;

public class CategoryGetDto {

    private String name;
    private List<Item> items;
    private Category parent;
    private List<Category> child;
}

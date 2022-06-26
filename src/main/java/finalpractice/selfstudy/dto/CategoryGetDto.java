package finalpractice.selfstudy.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finalpractice.selfstudy.entity.Category;
import finalpractice.selfstudy.entity.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CategoryGetDto {

    private String name;
    private List<Item> items; // todo : 여기서 item list 조회시 오류가 난다. 일대다 양방향 매핑 실정 2 이슈
    @JsonIgnore
    private Category parent;
    @JsonIgnore
    private List<Category> child;
}

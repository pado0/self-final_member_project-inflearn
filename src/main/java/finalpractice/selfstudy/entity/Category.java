package finalpractice.selfstudy.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import finalpractice.selfstudy.entity.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Category {

    @Id @GeneratedValue
    @Column(name = "category_id")
    private Long id;

    private String name;


    // 하나의 카테고리는 여러개의 아이템을 가질 수 있고, 아이템은 하나의 카테고리밖에 갖지 못한다.
    // https://galid1.tistory.com/774
    @OneToMany(mappedBy = "category")
    private List<Item> item = new ArrayList<>();

    // 자기참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id") // todo: 별도의 외래키 parent_id를 지정하는 이유? https://ocwokocw.tistory.com/135
    private Category parent;

    // 컬렉션은 필드에서 바로 초기화하는것이 null에서 안
    @OneToMany(mappedBy = "parent")
    @JsonIgnore
    private List<Category> child = new ArrayList<>();

    @JsonIgnore
    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}

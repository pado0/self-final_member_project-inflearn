package finalpractice.selfstudy.entity;

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
    @OneToMany(mappedBy = "category")
    private List<Item> item = new ArrayList<>();

    // 자기참조
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Category parent;

    // 컬렉션은 필드에서 바로 초기화하는것이 null에서 안
    @OneToMany(mappedBy = "parent")
    private List<Category> child = new ArrayList<>();

    public void addChildCategory(Category child) {
        this.child.add(child);
        child.setParent(this);
    }
}

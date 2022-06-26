package finalpractice.selfstudy.service;

import finalpractice.selfstudy.entity.Category;
import finalpractice.selfstudy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void joinCategory(Category category, Long parentCategoryId){
        // 카테고리 등록할때 부모카테고리를 선택하게 해야겠다. 자식은 연관관계 편의 메소드로.
        // Item과의 관계는 그냥 다대일이 낫겠다.

        /// child 추가해주기.
        //category.getParent().addChildCategory(category);

        // 카테고리 아이디로 parent 찾기
        Optional<Category> parentCategory = categoryRepository.findById(parentCategoryId);

        // parent 세팅해주기, parent의 child 세팅해주기.
        if (!parentCategory.isEmpty()) {
            category.setParent(parentCategory.get());
            parentCategory.get().addChildCategory(category);
        }


        categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> findById(Long id) {
        return categoryRepository.findById(id);
    }



}

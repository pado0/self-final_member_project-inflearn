package finalpractice.selfstudy.service;

import finalpractice.selfstudy.entity.Category;
import finalpractice.selfstudy.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryService {

    private final CategoryRepository categoryRepository;

    @Transactional
    public void joinCategory(Category category){
        // 카테고리 등록할때 부모카테고리를 선택하게 해야겠다. 자식은 연관관계 편의 메소드로.
        // Item과의 관계는 그냥 다대일이 낫겠다.

        categoryRepository.save(category);
    }

    public List<Category> findAllCategories() {
        return categoryRepository.findAll();
    }



}
